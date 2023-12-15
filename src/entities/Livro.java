package entities;

public class Livro extends Produto {
    private String autor;
    private int ano;

    public Livro(String nome, double preco, String autor, int ano) {
        super(nome, preco);
        this.autor = autor;
        this.ano = ano;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Nome: " + getNome());
        System.out.println("Id: " + getId());
        System.out.printf("Preço: R$ %.2f%n", getPreco());


        if (ano == 0) {
            System.out.println("Ano não especificado");
        } else {
            System.out.println("Ano: " + ano);
        }

        if (autor == null) {
            System.out.println("Autor não identificado");
        } else {
            System.out.println("Autor: " + autor);
        }

    }
}

