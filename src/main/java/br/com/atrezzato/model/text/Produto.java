package br.com.atrezzato.model.text;

public enum Produto {

    BRINCO("1", "Brinco"),
    CORRENTE("2", "Corrente"),
    PULSEIRA("3", "Pulseira"),
    ANEL("4", "Anel"),
    PINGENTE("5", "Pingente");


    private String codigo;
    private String descricao;

    Produto(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Produto[] produtos(){
        int j = 0;
        Produto[] produto = new Produto[5];
        for (int i = 0; i < Produto.values().length; i++) {
            produto[j] = Produto.values()[i];
            j++;
        }
        return produto;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}
