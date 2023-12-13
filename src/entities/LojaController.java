package entities;

import java.util.Scanner;

public class LojaController {
    private final Scanner scanner;
    private final Loja loja;

    public LojaController(Scanner scanner) {
        this.loja = new Loja();
        this.scanner = scanner;
    }

    public void adicionarMangasAutomaticamente() {
        loja.adicionarProduto(new Manga("One Piece", 19.99,  "Eiichiro Oda"));
        loja.adicionarProduto(new Manga("Naruto", 14.99, "Masashi Kishimoto"));
        loja.adicionarProduto(new Manga("Attack on Titan", 17.99, "Hajime Isayama"));
        loja.adicionarProduto(new Manga("Cavaleiros do Zodíaco", 24.99, "Masami Kurumada"));
        loja.adicionarProduto(new Livro("Dracula", 59.99, "Bram Stoker", 1999));
        loja.adicionarProduto(new Livro("The Scary Crow Who Walks at Midnight", 84.99, "R.L. Stine", 1994));
        loja.adicionarProduto(new Livro("Frankenstein", 169.99, "Mary Shelley", 1818));
    }

    public void exibirMenu() {
        System.out.println("------Bem vindo a Loja------");
        System.out.println("O que você gostaria de fazer:");
        System.out.println("1 - Mostrar o Estoque");
        System.out.println("2 - Adicionar um produto");
        System.out.println("3 - Remover um produto");
        System.out.println("4 - Atualizar um produto");
    }

    public void processarOpcao(int escolha) {
        int id = 0;
        switch (escolha) {
            case 1:
                loja.exibirEstoque();
                break;
            case 2:
                System.out.println("------Existem alguns tipos de Produto------");
                System.out.println("1 - Manga");
                System.out.println("2 - Livro");
                System.out.print("Qual tipo de Produto: ");
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
            case 3:
                System.out.print("Digite o id do produto que você quer remover: ");
                id = scanner.nextInt();
                Produto produtoId = loja.removerProdutoByID(id);
                System.out.println("Produto sendo Removido!");
                produtoId.exibirInfo();
                System.out.println("Produto Removido com Sucesso!");
                break;
            case 4:
                System.out.print("Digite o id do Produto que você quer atualizar:");
                id = scanner.nextInt();
                scanner.nextLine();
                boolean attValida = loja.atualizarProduto(id);

                if (attValida) {
                    System.out.print("Deseja editar este produto? (S/N): ");
                    char resposta = scanner.nextLine().toUpperCase().charAt(0);

                    if (resposta == 'S') {
                        Produto produto = loja.buscarProdutoPorId(id);
                        if (produto != null) {
                            Produto novoProduto = loja.solicitarNovasInformacoes(scanner, produto);


                            produto.setNome(novoProduto.getNome());
                            produto.setPreco(novoProduto.getPreco());

                            if (produto instanceof Manga && novoProduto instanceof Manga) {
                                ((Manga) produto).setAutor(((Manga) novoProduto).getAutor());
                            }

                            if (produto instanceof Livro && novoProduto instanceof Livro) {
                                ((Livro) produto).setAutor(((Livro) novoProduto).getAutor());
                                ((Livro) produto).setAno(((Livro) novoProduto).getAno());
                            }

                            System.out.println("Produto atualizado com sucesso!");
                        } else {
                            System.out.println("Produto com o ID " + id + " não encontrado.");
                        }
                    } else {
                        System.out.println("Edição cancelada pelo usuário.");
                    }
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
