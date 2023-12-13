package entities;


abstract class Produto {
    private  String nome;
    private int id;
    private  double preco;

    private int tipo;



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

    public String setNome(String nome) {
       return this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public abstract void exibirInfo();
}