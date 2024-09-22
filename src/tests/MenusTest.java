package tests;

import java.util.Scanner;

public class MenusTest {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        while (true) {

            System.out.println("=====================================");
            System.out.println("|           Menu Principal           |");
            System.out.println("=====================================");
            System.out.println("|  1. Créer un nouveau projet        |");
            System.out.println("|  2. Afficher les projets existants |");
            System.out.println("|  3. Calculer le coût d'un projet   |");
            System.out.println("|  4. Quitter                        |");
            System.out.println("=====================================");
            System.out.print("---> ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    GestionClientTest.NouveauProjet();
                    break;
                case 2:
                        GestionProjetTest.displayallprojet();
                    break;
                case 3:

                    break;
                case 4:

                    System.out.println(" Au revoir !");
                    return;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }

    }
}
