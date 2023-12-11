package entities;

abstract class Produto {
    private String nome;
    private int id;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.id = 0;
        this.preco = preco;
    }


    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }

    public abstract void exibirInfo();
}