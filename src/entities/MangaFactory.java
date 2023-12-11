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
