package entities;


abstract class Produto {
    private String nome;
    private int id;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
        this.id = Loja.gerarProximoId();
    }


    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public abstract void exibirInfo();
}