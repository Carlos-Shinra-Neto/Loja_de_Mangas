package entities;

import java.util.Scanner;

public class LojaController {
    private Scanner scanner;
    private Loja loja;

    public LojaController(Scanner scanner) {
        this.loja = new Loja();
        this.scanner = scanner;
    }

    public void adicionarMangasAutomaticamente() {

    }

    public void exibirMenu() {
        System.out.println("------Bem vindo a Loja------");
        System.out.println("O que você gostaria de fazer:");
        System.out.println("1 - Mostrar o Estoque");
        System.out.println("2 - Adicionar um produto");
    }

    public void processarOpcao(int escolha) {
        switch (escolha) {
            case 1:
                loja.exibirEstoque();
                break;
            case 2:
                System.out.println("------Existem alguns tipos de Produto------");
                System.out.println("1 - Manga");
                System.out.println("2 - Livro");
                System.out.printf("Qual tipo de Produto: ");
                int escolha2 = scanner.nextInt();
                scanner.nextLine();
                switch (escolha2) {
                    case 1:
                        System.out.println("Adicionar Manga:");
                        Produto novoManga = MangaFactory.criarProduto(scanner);
                        loja.adicionarProduto(novoManga);
                        System.out.println("Manga adicionado com sucesso!");
                        break;
                    case 2:
                        System.out.println("Adicionar Livro:");
                        Produto novoLivro = LivroFactory.criarProduto(scanner);
                        loja.adicionarProduto(novoLivro);
                        System.out.println("Livro adicionado com sucesso!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
                break;
            case 0:
                System.out.println("Encerrando o programa. Obrigado!");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

}
