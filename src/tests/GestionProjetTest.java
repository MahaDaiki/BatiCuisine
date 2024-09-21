package tests;

import entities.Projet;
import enums.EtatProjet;
import services.implementations.ProjetServiceImpl;
import services.interfaces.ProjetService;

import java.util.Scanner;

public class GestionProjetTest {
    static final Scanner scanner = new Scanner(System.in);
    static final ProjetService projetService = new ProjetServiceImpl();
    public static void main(String[] args) {

    }

        static void ajouterUnProjet(int clientId){


            System.out.print("Entrez le nom du projet: ");
            String nomProjet = scanner.nextLine();

            System.out.print("Entrez la surface du projet: ");
            double surface = scanner.nextDouble();

            Projet projet = new Projet(null, nomProjet, null, null, EtatProjet.EnCours, surface, clientId);


            projetService.addProjet(projet, clientId);
            lastinsertedprojet();

        }

        public static void lastinsertedprojet(){
            int projetId = projetService.getLastInsertedProjetId();

            if (projetId != -1) {
                System.out.println("Projet ajouté avec succès " );
                ComposantMenu(projetId);
            } else {
                System.out.println("Erreur lors de l'obtention de l'ID du projet.");
            }
        }

    public static void ComposantMenu(int projetId) {
        int choice;
        do {
            System.out.println("\n---Ajouter Des Composant ---");
            System.out.println("1. Ajouter de nouveaux matériaux");
            System.out.println("2. Ajouter de la main-d'œuvre");
            System.out.println("3. Retourner au menu principal");
            System.out.print("----> ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    MateriauxTest.AddMateriaux(projetId);
                    break;
                case 2:
                    GestionDesMainDoeuvreTest.AddMainDoeuvre(projetId);
                    break;
                case 3:
                    System.out.println("Retour au menu principal.");
                    GestionClientTest.NouveauProjet();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choice != 3);
    }


}

