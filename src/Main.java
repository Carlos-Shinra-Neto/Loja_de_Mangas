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

