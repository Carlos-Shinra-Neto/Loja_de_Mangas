# Loja

## Visão Geral

O projeto "Loja" é uma aplicação simples que simula o funcionamento básico de uma loja virtual. A implementação é orientada a objetos, utilizando conceitos como classes, herança, polimorfismo e encapsulamento. O objetivo é proporcionar uma experiência prática no desenvolvimento de software, aplicando os princípios fundamentais da programação orientada a objetos (POO).

## Estrutura do Projeto

O projeto é organizado em classes distintas para facilitar a manutenção e extensibilidade do código. As principais classes são:

- **Main:** Classe principal que inicia a execução do programa. Instancia um `LojaController` para gerenciar a interação com o usuário.

```java
import entities.LojaController;
import exceptions.ProdutoNaoEncontradoException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) throws ProdutoNaoEncontradoException {
      Scanner scanner = new Scanner(System.in);
      LojaController lojaController = new LojaController(scanner);

      lojaController.adicionarMangasAutomaticamente();

      int escolha = -1;
      do {
         try {
            lojaController.exibirMenu();
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine();

            lojaController.processarOpcao(escolha);
         } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira uma opção válida.");
            scanner.nextLine();
         } catch (ProdutoNaoEncontradoException e){
            System.out.println(e.getMessage());
         }
      } while (escolha != 0);

      scanner.close();
   }

}
`````
- **LojaController:** Responsável por orquestrar as operações da loja, exibir o menu e processar as escolhas do usuário. Mantém uma instância da classe `Loja`.
`````java
package entities;

import exceptions.ProdutoNaoEncontradoException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LojaController {
    private final Scanner scanner;
    private final Loja loja;

    public LojaController(Scanner scanner) {
        this.loja = new Loja();
        this.scanner = scanner;
    }

    public void adicionarMangasAutomaticamente() {
        loja.adicionarProduto(new Manga("One Piece", 19.99, "Eiichiro Oda"));
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
        System.out.println("5 - Pesquisar um produto");
        System.out.println("6 - Comprar um produto");
        System.out.println("0 - Encerrar o programa");
    }

    public void processarOpcao(int escolha) throws ProdutoNaoEncontradoException {
        int id = 0;
        try {
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
                    try {
                        switch (escolha2) {
                            case 1:
                                scanner.nextLine();
                                System.out.println("Adicionar Manga:");
                                Produto novoManga = MangaFactory.criarProduto(scanner);
                                loja.adicionarProduto(novoManga);
                                System.out.println("Manga adicionado com sucesso!");
                                break;
                            case 2:
                                scanner.nextLine();
                                System.out.println("Adicionar Livro:");
                                Produto novoLivro = LivroFactory.criarProduto(scanner);
                                loja.adicionarProduto(novoLivro);
                                System.out.println("Livro adicionado com sucesso!");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    System.out.print("Digite o id do produto que você quer remover: ");
                    id = scanner.nextInt();
                    try {
                        scanner.nextLine();
                        Produto produtoId = loja.removerProdutoByID(id);
                        System.out.println("Produto sendo Removido!");
                        produtoId.exibirInfo();
                        System.out.println("Produto Removido com Sucesso!");
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.nextLine();
                    }
                    break;
                case 4:
                    System.out.print("Digite o id do Produto que você quer atualizar:");
                    id = scanner.nextInt();
                    try {
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
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.nextLine();
                    }
                    break;
                case 5:
                    System.out.print("Digite o ID que você quer procurar");
                    id = scanner.nextInt();
                    try {
                        loja.buscarProdutoPorId(id);
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.nextLine();
                    }
                    break;
                case 6:
                    System.out.println("Qual o produto que você quer comprar?");
                    System.out.print("Digite o ID do produto: ");
                    id = scanner.nextInt();
                    try {
                        if (loja.buscarProdutoPorId(id) != null) {
                            scanner.nextLine();
                            System.out.print("Deseja comprar este produto? (S/N): ");
                            char resposta = scanner.nextLine().toUpperCase().charAt(0);
                            if (resposta == 'S') {
                                loja.comprarProduto(id);
                            } else {
                                System.out.println("Compra cancelada pelo usuário.");
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.nextLine();
                    }
                    break;
                case 0:
                    System.out.println("Encerrando o programa. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.nextLine();
        }
    }
}

- `````
- **Loja:** Gerencia o estoque de produtos, armazenando uma lista de produtos. Contém métodos para adicionar, exibir, remover, atualizar e comprar produtos, bem como para gerar IDs sequenciais.
````java
package entities;

import exceptions.ProdutoNaoEncontradoException;

import java.util.ArrayList;
import java.util.Scanner;

public class Loja {

    private static ArrayList<Produto> estoque;
    private static int proximoId;

    public Loja() {
        estoque = new ArrayList<>();
        proximoId = 1;
    }

    public void adicionarProduto(Produto produto) {
        produto.setId(gerarProximoId());
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

    public static int gerarProximoId() {
        for (Produto produto : estoque) {
            if (produto.getId() == proximoId) {
                proximoId++;
                return gerarProximoId();
            }
        }
        return proximoId;
    }

    public boolean atualizarProduto(int id) throws ProdutoNaoEncontradoException {
        for (Produto produto : estoque) {
            if (produto.getId() == id) {
                System.out.println("Informações atuais do produto:");
                produto.exibirInfo();
                return true;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto com o ID " + id + " não encontrado.");
    }

    public Produto buscarProdutoPorId(int id) throws ProdutoNaoEncontradoException {
        for (Produto produto : estoque) {
            if (produto.getId() == id) {
                produto.exibirInfo();
                return produto;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto com o ID " + id + " não encontrado.");
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

    public Produto removerProdutoByID(int id) throws ProdutoNaoEncontradoException {
        for (int i = 0; i < estoque.size(); i++) {
            Produto produto = estoque.get(i);
            if (id == produto.getId()) {
                estoque.remove(i);
                return produto;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto com o ID " + id + " não encontrado.");
    }

    public Produto comprarProduto(int id) throws ProdutoNaoEncontradoException{
        for (int i = 0; i < estoque.size(); i++) {
            Produto produto = estoque.get(i);
            if(produto.getId() == id){
                System.out.println("Produto comprado com Sucesso!");
                estoque.remove(i);
                return produto;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto com o ID " + id + " não encontrado.");
    }
}

- ````

- **Produto:** Classe abstrata que representa um produto +genérico. Possui atributos comuns a todos os produtos, como nome, ID e preço.
````Java
package entities;


abstract class Produto {
    private  String nome;
    private int id;
    private  double preco;

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

````
- **Manga:** Subclasse de `Produto`, especializada para representar mangas. Adiciona atributos específicos, como o nome do autor.
````Java
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
        System.out.println("Preço: R$" + getPreco());

        if (autor != null) {
            System.out.println("Autor: " + autor);
        } else {
            System.out.println("Autor não identificado");
        }

    }
}
````

- **MangaFactory:** Implementa um Factory Method para criar instâncias de mangas, facilitando a criação de objetos de maneira consistente.

````Java
package entities;

import java.util.Scanner;
public class MangaFactory {

    public static Produto criarProduto(Scanner scanner){
        System.out.print("Digite o nome do manga: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o preço do manga: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o nome do autor: ");
        String autor = scanner.nextLine();

        return new Manga(nome, preco, autor);
    }
}

````

- **Livro:** Subclasse de `Produto`, especializada para representar mangas. Adciona atributos específicos, como o nome do autor e ano da edição.

````Java
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
        System.out.println("Preço: R$" + getPreco());

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
````

- **LivroFactory:** Subclasse de `Produto`, especializada para representar livros. Adiciona atributos específicos, como o nome do autor e ano da edição.
````Java
package entities;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class LivroFactory {
    public static Produto criarProduto(Scanner scanner){
        System.out.print("Digite o nome do Livro: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o preço do Livro: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o nome do autor: ");
        String autor = scanner.nextLine();

        System.out.print("Qual o ano de edição: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        return new Livro(nome, preco, autor, ano);
    }
}

````
- **ProdutoNaoEncontradoException:** Classe de Excpetions que vai lidar com alguns erros que podem vir a acontecer no código.
````java
package exceptions;

public class ProdutoNaoEncontradoException extends Exception {
    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

- ````

## Funcionalidades Principais

### 1. Adição de Mangas

A classe `LojaController` permite ao usuário adicionar novos produtos à loja. Utiliza o switch case para definir qual tipo de produto e chamar o certo `Factory` para criar instâncias dos produtos.

### 2. Exibição do Estoque

O usuário pode visualizar o estoque atual da loja, exibindo informações sobre cada produto.

### 3. Pesquisa de Produtos

A classe `Loja` fornece métodos para buscar produtos pelo ID.

### 4. Remoção de Produtos

Possibilidade de remover um produto do estoque por ID.

### 5. Atualização de Produtos

Possibilidade de atualizar um produto do estoque via ID.

### 6. Compra de Produtos

Permite ao usuário comprar um produto, removendo-o do estoque.

### 6. Geração Automática de IDs

A classe `Loja` implementa um método para gerar IDs sequenciais automaticamente, facilitando a criação de novos produtos.

### 7. Manipulação de Exceções

Introdução de uma exceção personalizada, `ProdutoNaoEncontrado`, para lidar com casos em que um produto não é encontrado.

## Uso Básico

1. **Exibir o Estoque:**
    - Selecione a opção `1` no menu.
    - Visualize as informações sobre os produtos no estoque.
    
2.  **Adicionar um Produto:**
    - Escolha a opção `2` no menu.
    - Insira os dados do produto quando solicitado.

3. **Remover um Produto:**
    - Escolha a opção `3` no menu.
    - Insira o ID do produto que você deseja remover.

4. **Atualizr um Produto:**
    - Escolha a opção `4` no menu.
    - Insira o ID do produto que deseja atualizar.
    - Confirme que o produto é o mesmo que você deseja alterar.
    - Altere os dados necessários do produto.
   
5. **Pesquisar um Produto:**
   - Selecione a opção `5` no menu.
   - Insira oesquis ID do produto que você deseja par.

6. **Comprar um Produto:**
    - Selecione a opção `6` no menu.
    - Insira o ID do manga que você deseja comprar.
    - E confirme a compra.

7. **Encerrar o Programa:**
    - Selecione a opção `0` no menu.

## Melhorias e Expansões Futuras

1. **Persistência de Dados:**
    - Adição de persistência de dados para salvar e carregar informações do estoque entre execuções do programa.
2. **Validação de Entradas:**
    - Implementação de verificações e validações para garantir que as entradas do usuário estejam corretas.
3. **Novos Tipos de Produtos:**
    - Expansão do sistema para lidar com diferentes tipos de produtos além de mangas e livros.


## Conclusão

O projeto "Loja" oferece uma oportunidade prática para aplicar e consolidar os conhecimentos adquiridos em programação orientada a objetos. Ao experimentar com o código, adicionar novas funcionalidades e explorar possíveis melhorias, os desenvolvedores têm a chance de aprimorar suas habilidades e compreender a aplicação dos princípios de POO na prática.

