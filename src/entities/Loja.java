package entities;

import java.util.ArrayList;
import java.util.Collections;

public class Loja {

    private static ArrayList<Produto> estoque;
    private static int proximoId;

    public Loja() {
        this.estoque = new ArrayList<>();
        this.proximoId = 1;
    }

    public static int gerarProximoId() {
        for (Produto produto : estoque) {
            if (produto.getId() == proximoId) {
                proximoId++;
                return gerarProximoId();
            }
        }
        return proximoId;
    }


        public void adicionarProduto(Produto produto) {
        produto.setId(gerarProximoId());
        estoque.add(produto);
    }


    public void exibirEstoque() {
        if (estoque == null || estoque.isEmpty()) {
            System.out.println("Não possui um estoque ainda ou o estoque está vazio.");
        } else {
            Collections.sort(estoque, (produto1, produto2)
                    -> Integer.compare(produto1.getId(), produto2.getId()));

            System.out.println("===== Estoque da Loja de Mangas =====");
            for (Produto produto : estoque) {
                produto.exibirInfo();
                System.out.println("------------------------------");
            }
        }
    }


    public Produto removerProdutoByID(int id) {
        for (int i = 0; i < estoque.size(); i++) {
            Produto produto = estoque.get(i);
            if (produto.getId() == id) {
                estoque.remove(i);
                proximoId = 1;
                return produto;
            }
        }
        return null;
    }
}
