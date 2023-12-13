package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Loja {

    private static ArrayList<Produto> estoque;
    private static int proximoId;

    public Loja() {
        estoque = new ArrayList<>();
        proximoId = 1;
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

    public boolean atualizarProduto(int id) {
        for (Produto produto : estoque) {
            if (produto.getId() == id) {
                System.out.println("Informações atuais do produto:");
                produto.exibirInfo();
                return true;
            }
        }
        System.out.println("Produto com o ID " + id + " não encontrado.");
        return false;
    }

    public Produto buscarProdutoPorId(int id) {
        for (Produto produto : estoque) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        System.out.println("Produto com o ID " + id + " não encontrado.");
        return null;
    }

    public Produto solicitarNovasInformacoes(Scanner scanner, Produto produtoAtual) {
        System.out.println("Digite as novas informações do produto:");

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        System.out.print("Novo preço: ");
        double novoPreco = scanner.nextDouble();
        scanner.nextLine();

        if (produtoAtual instanceof Manga) {
            System.out.print("Novo autor do Manga: ");
            String novoAutor = scanner.nextLine();
            ((Manga) produtoAtual).setAutor(novoAutor);
        }

        if (produtoAtual instanceof Livro) {
            System.out.print("Novo autor do Livro: ");
            String novoAutor = scanner.nextLine();

            System.out.print("Novo ano do Livro: ");
            int novoAno = scanner.nextInt();
            scanner.nextLine();
            ((Livro) produtoAtual).setAutor(novoAutor);
            ((Livro) produtoAtual).setAno(novoAno);
        }

        produtoAtual.setNome(novoNome);
        produtoAtual.setPreco(novoPreco);

        return produtoAtual;
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


    public Produto removerProdutoByID(int id) {
        for (int i = 0; i < estoque.size(); i++) {
            Produto produto = estoque.get(i);
            if (id == produto.getId()) {
                estoque.remove(i);
                return produto;
            }
        }
        return null;
    }
}
