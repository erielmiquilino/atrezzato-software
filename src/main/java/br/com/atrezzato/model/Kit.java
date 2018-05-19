package br.com.atrezzato.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Kit {


    private Integer codigo;

    private BigDecimal valorTotal;

    private Integer totalItens;

    private List<KitItem> kitItems = new ArrayList<>();

    private LocalDate dataEmissao;


    public List<KitItem> getKitItems() {
        return kitItems;
    }

    public void setKitItems(List<KitItem> kitItems) {
        this.kitItems = kitItems;
    }

    public Integer getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(Integer totalItens) {
        this.totalItens = totalItens;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }


}
