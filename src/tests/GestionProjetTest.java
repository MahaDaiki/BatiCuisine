package tests;

import entities.Client;
import entities.Projet;
import enums.EtatProjet;
import services.implementations.ProjetServiceImpl;
import services.interfaces.ProjetService;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GestionProjetTest {
    static final Scanner scanner = new Scanner(System.in);
    static final ProjetService projetService = new ProjetServiceImpl();
    public static void main(String[] args) {
        displayallprojet();
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

    static void displayallprojet(){

            List<Projet> projets = projetService.getAllProjets();

            for (Projet projet : projets) {
                System.out.println("*=========================================*");
                System.out.println( "---> "+ projet.getProjetId() + "_ Nom du Projet: " + projet.getNomProjet());
                System.out.println("|     Marge Bénéficiaire: " + projet.getMargeBeneficiaire());
                System.out.println("|     Coût Total: " + projet.getCoutTotal());
                System.out.println("|     État du Projet: " + projet.getEtat_projet());
                System.out.println("|     Surface: " + projet.getSurface());
                System.out.println("|     Client ID: " + projet.getClientId());

            }
           System.out.println("\n-----------------------");
            System.out.println("1_ Detail Du Projet.");
            System.out.println("2_ <------ Menu");
        System.out.print("----> ");

       int choice = scanner.nextInt();
        scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.print("Entrez le projet pour plus de détails: ");
                    int projetId = scanner.nextInt();
                    scanner.nextLine();
                    displayprojetdetails(projetId);
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez ressayer.");

            }
    }

    static void displayprojetdetails(int projetId){
        HashMap<String, Object> details = projetService.getProjectAndClientDetails(projetId);

        if (details.isEmpty()) {
            System.out.println("No project found with ID: " + projetId);
        } else {
            Projet projet = (Projet) details.get("projet");
            Client client = (Client) details.get("client");

            System.out.println("Project Details: " + projet);

            if (client != null) {
                System.out.println("Client Details: " + client);
            } else {
                System.out.println("No associated client found.");
            }
        }
    }

}

