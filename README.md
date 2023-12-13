# Loja

## Visão Geral

O projeto "Loja" é uma aplicação simples que simula o funcionamento básico de uma loja virtual. A implementação é orientada a objetos, utilizando conceitos como classes, herança, polimorfismo e encapsulamento. O objetivo é proporcionar uma experiência prática no desenvolvimento de software, aplicando os princípios fundamentais da programação orientada a objetos (POO).

## Estrutura do Projeto

O projeto é organizado em classes distintas para facilitar a manutenção e extensibilidade do código. As principais classes são:

- **Main:** Classe principal que inicia a execução do programa. Instancia um `LojaController` para gerenciar a interação com o usuário.

```java
import entities.LojaController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LojaController lojaController = new LojaController(scanner);
        lojaController.adicionarMangasAutomaticamente();

        int escolha;
        do {
            lojaController.exibirMenu();
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine();

            lojaController.processarOpcao(escolha);
        } while (escolha != 0);

        scanner.close();
    }
}
`````
- **LojaController:** Responsável por orquestrar as operações da loja, exibir o menu e processar as escolhas do usuário. Mantém uma instância da classe `Loja`.

- **Loja:** Gerencia o estoque de produtos, armazenando uma lista de produtos. Contém métodos para adicionar, exibir, remover, atualizar e comprar produtos, bem como para gerar IDs sequenciais.

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

