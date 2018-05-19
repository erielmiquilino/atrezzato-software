package br.com.atrezzato.model;

import br.com.atrezzato.model.text.Produto;

import java.math.BigDecimal;
import java.util.Objects;

public class KitItem {

    private Produto produto;

    private String referencia;

    private BigDecimal quantidade;

    private BigDecimal valorUnitario;

    private BigDecimal valorTotal;


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KitItem kitItem = (KitItem) o;
        return produto == kitItem.produto &&
                Objects.equals(referencia, kitItem.referencia) &&
                Objects.equals(quantidade, kitItem.quantidade) &&
                Objects.equals(valorUnitario, kitItem.valorUnitario) &&
                Objects.equals(valorTotal, kitItem.valorTotal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(produto, referencia, quantidade, valorUnitario, valorTotal);
    }

    @Override
    public String toString() {
        return produto.getCodigo();
    }

}
