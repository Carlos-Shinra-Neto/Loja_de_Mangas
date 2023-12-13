# Loja de Mangas

## Visão Geral

O projeto "Loja de Mangas" é uma aplicação simples que simula o funcionamento básico de uma loja virtual especializada em mangas. A implementação é orientada a objetos, utilizando conceitos como classes, herança, polimorfismo e encapsulamento. O objetivo é proporcionar uma experiência prática no desenvolvimento de software, aplicando os princípios fundamentais da programação orientada a objetos (POO).

## Estrutura do Projeto

O projeto é organizado em classes distintas para facilitar a manutenção e extensibilidade do código. As principais classes são:

- **Main:** Classe principal que inicia a execução do programa. Instancia um `LojaController` para gerenciar a interação com o usuário.

- **LojaController:** Responsável por orquestrar as operações da loja, exibir o menu e processar as escolhas do usuário. Mantém uma instância da classe `LojaMangas`.

- **LojaMangas:** Gerencia o estoque de mangas, armazenando uma lista de produtos. Contém métodos para adicionar, exibir e remover mangas, bem como para gerar IDs sequenciais.

- **Produto:** Classe abstrata que representa um produto +genérico. Possui atributos comuns a todos os produtos, como nome, ID e preço.

- **Manga:** Subclasse de `Produto`, especializada para representar mangas. Adiciona atributos específicos, como o nome do autor.

- **MangaFactory:** Implementa um Factory Method para criar instâncias de mangas, facilitando a criação de objetos de maneira consistente.

## Funcionalidades Principais

### 1. Adição de Mangas

A classe `LojaController` permite ao usuário adicionar novos mangas à loja. Utiliza o Factory Method `MangaFactory.criarManga` para criar instâncias de mangas.

### 2. Exibição do Estoque

O usuário pode visualizar o estoque atual da loja, exibindo informações sobre cada manga.

### 3. Pesquisa de Mangas

A classe `LojaMangas` fornece métodos para buscar mangas pelo ID.

### 4. Compra de Mangas

Permite ao usuário comprar um manga, removendo-o do estoque.

### 5. Remoção de Mangas

Possibilidade de remover um manga do estoque, seja por ID ou pelo objeto manga.

### 6. Geração Automática de IDs

A classe `LojaMangas` implementa um método para gerar IDs sequenciais automaticamente, facilitando a criação de novos produtos.

### 7. Manipulação de Exceções

Introdução de uma exceção personalizada, `MangaNaoEncontradoException`, para lidar com casos em que um manga não é encontrado.

## Uso Básico

1. **Exibir o Estoque:**
    - Selecione a opção `1` no menu.
    - Visualize as informações sobre os mangas no estoque.
    
2.  **Adicionar um Mangá:**
    - Escolha a opção `2` no menu.
    - Insira o nome, preço e autor do produto quando solicitado.

3. **Remover um Manga:**
    - Escolha a opção `3` no menu.
    - Insira o ID do manga que você deseja remover.

4. **Pesquisar um Manga:**
    - Escolha a opção `4` no menu.
    - Insira o ID do manga desejado.

5. **Comprar um Manga:**
    - Selecione a opção `5` no menu.
    - Insira o ID do manga que você deseja comprar.
  
6. **Pesquisar um Produto:**
    - Selecione a opção `6` no menu.
    - Insira o ID do produto que você deseja comprar.
   
7. **Encerrar o Programa:**
    - Selecione a opção `0` no menu.

## Melhorias e Expansões Futuras

1. **Persistência de Dados:**
    - Adição de persistência de dados para salvar e carregar informações do estoque entre execuções do programa.
2. **Validação de Entradas:**
    - Implementação de verificações e validações para garantir que as entradas do usuário estejam corretas.

3. **Novos Tipos de Produtos:**
    - Expansão do sistema para lidar com diferentes tipos de produtos além de mangas.


## Conclusão

O projeto "Loja de Mangas" oferece uma oportunidade prática para aplicar e consolidar os conhecimentos adquiridos em programação orientada a objetos. Ao experimentar com o código, adicionar novas funcionalidades e explorar possíveis melhorias, os desenvolvedores têm a chance de aprimorar suas habilidades e compreender a aplicação dos princípios de POO na prática.

