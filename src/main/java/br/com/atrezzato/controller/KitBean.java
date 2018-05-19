package br.com.atrezzato.controller;

import br.com.atrezzato.model.Kit;
import br.com.atrezzato.model.KitItem;
import br.com.atrezzato.model.text.Produto;
import br.com.atrezzato.util.UtilReports.UtilReports;
import br.com.atrezzato.util.jsf.FacesUtil;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Named
@ViewScoped
public class KitBean implements Serializable {

    private Kit kit;

    private KitItem kitItem;

    private int brincos;
    private int correntes;
    private int pulseiras;
    private int aneis;
    private int pingentes;


    private Produto produto;

    @PostConstruct
    public void init  () {
        kit = new Kit();
        kitItem = new KitItem();
    }

    public void incluirProduto() {
        try {
            if (produto.getDescricao().equals("Brinco")){
                brincos++;
            } else if (produto.getDescricao().equals("Corrente")){
                correntes++;
            } else if (produto.getDescricao().equals("Pulseira")){
                pulseiras++;
            } else if (produto.getDescricao().equals("Anel")){
                aneis++;
            } else if (produto.getDescricao().equals("Pingente")){
                pingentes++;
            }

            kit.setTotalItens(brincos+correntes+pulseiras+aneis+pingentes);

            kitItem.setProduto(produto);
            kitItem.setReferencia(kitItem.getValorUnitario().toString().replaceAll("\\.", "")+"X");
            kitItem.setQuantidade(BigDecimal.valueOf(1));
            kitItem.setValorTotal(kitItem.getValorUnitario().multiply(kitItem.getQuantidade()));
            kit.getKitItems().add(kitItem);
            kitItem = new KitItem();

        } catch (NullPointerException e) {
            kitItem = new KitItem();
            FacesUtil.addErrorMessage("Produto deve ser informado");
        }
    }

    public void removerProduto(KitItem kitItem) {
        if (kitItem.getProduto().getDescricao().equals("Brinco")){
            brincos--;
        } else if (kitItem.getProduto().getDescricao().equals("Corrente")){
            correntes--;
        } else if (kitItem.getProduto().getDescricao().equals("Pulseira")){
            pulseiras--;
        } else if (kitItem.getProduto().getDescricao().equals("Anel")){
            aneis--;
        } else if (kitItem.getProduto().getDescricao().equals("Pingente")){
            pingentes--;
        }
        kit.setTotalItens(brincos+correntes+pulseiras+aneis+pingentes);

        kit.getKitItems().remove(kitItem);
    }

    public void gerarFicha() throws Exception {

        for (int i = 0; i < kit.getKitItems().size() ; i++) {
            int quantidade = 0;

            for (KitItem kitItem_j: kit.getKitItems()) {
                if (kit.getKitItems().get(i).getProduto().equals(kitItem_j.getProduto()) && kit.getKitItems().get(i).getReferencia().equals(kitItem_j.getReferencia())) {
                    quantidade ++;
                }
            }

            kit.getKitItems().get(i).setQuantidade(BigDecimal.valueOf(quantidade));
            kit.getKitItems().get(i).setValorTotal(kit.getKitItems().get(i).getValorUnitario().multiply(kit.getKitItems().get(i).getQuantidade()));
        }


        Set<KitItem> kitItens = new HashSet<>();

        for (KitItem kitItem: kit.getKitItems()) {
            kitItens.add(kitItem);
        }


        List<KitItem> itemList = new ArrayList<>( kitItens);
        itemList.sort(Comparator.comparing(KitItem::toString));

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("numeroFicha", "Nº " + kit.getCodigo());

        InputStream relatorioStream = getClass().getResourceAsStream("/reports/ficha.jasper");
        JasperPrint print = JasperFillManager.fillReport(relatorioStream, parametros, new JRBeanCollectionDataSource(itemList, false));

        byte[] pdf = JasperExportManager.exportReportToPdf(print);

        File report = new File("/Atrezzato/temp/" + "temp" + System.currentTimeMillis() + ".pdf");
        FileUtils.writeByteArrayToFile(report, pdf);

        UtilReports.abrirRelatorioNovaJanela(report);

    }

    public Kit getKit() {
        return kit;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public KitItem getKitItem() {
        return kitItem;
    }

    public void setKitItem(KitItem kitItem) {
        this.kitItem = kitItem;
    }

    public Produto[] getProdutos() {
        return Produto.produtos();
    }

    public int getBrincos() {
        return brincos;
    }

    public void setBrincos(int brincos) {
        this.brincos = brincos;
    }

    public int getCorrentes() {
        return correntes;
    }

    public void setCorrentes(int correntes) {
        this.correntes = correntes;
    }

    public int getPulseiras() {
        return pulseiras;
    }

    public void setPulseiras(int pulseiras) {
        this.pulseiras = pulseiras;
    }

    public int getAneis() {
        return aneis;
    }

    public void setAneis(int aneis) {
        this.aneis = aneis;
    }

    public int getPingentes() {
        return pingentes;
    }

    public void setPingentes(int pingentes) {
        this.pingentes = pingentes;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
