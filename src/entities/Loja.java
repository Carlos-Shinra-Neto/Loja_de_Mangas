package entities;

import java.util.ArrayList;

public class Loja {

    private ArrayList<Produto> estoque;
    private static int proximoId;
    public Loja() {
        this.estoque = new ArrayList<>();
    }


    public void adicionarProduto(Produto produto) {
        estoque.add(produto);
    }

    public void exibirEstoque() {
        if (estoque == null || estoque.isEmpty()) {
            System.out.println("Não possui um estoque ainda ou o estoque está vazio.");
        } else {
            System.out.println("===== Estoque da Loja de Mangas =====");
            for (Produto produto : estoque) {
                produto.exibirInfo();
                System.out.println("------------------------------");
            }
        }
    }
}
