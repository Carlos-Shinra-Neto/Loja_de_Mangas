package entities;

public class Manga extends Produto {

    private String autor;

    public Manga(String nome, double preco, String autor) {
        super(nome, preco);
        this.autor = autor;

    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Nome: " + getNome());
        System.out.println("Id: " + getId());
        System.out.printf("Preço: R$ %.2f%n", getPreco());

        if (autor != null) {
            System.out.println("Autor: " + autor);
        } else {
            System.out.println("Autor não identificado");
        }

    }
}

