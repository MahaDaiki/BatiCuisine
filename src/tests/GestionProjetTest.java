package tests;

import entities.Client;
import entities.Projet;
import enums.EtatProjet;
import repositories.implementations.MainDoeuvreRepositoryImpl;
import repositories.implementations.MateriauxRepositoryImpl;
import services.implementations.MainDoeuvreServiceImpl;
import services.implementations.MateriauxServiceImpl;
import services.implementations.ProjetServiceImpl;
import services.interfaces.MainDoeuvreService;
import services.interfaces.MateriauxService;
import services.interfaces.ProjetService;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GestionProjetTest {
    static final Scanner scanner = new Scanner(System.in);
    static final ProjetService projetService = new ProjetServiceImpl();
    static final MainDoeuvreService mainDoeuvreService = new MainDoeuvreServiceImpl(new MainDoeuvreRepositoryImpl());
    static final MateriauxService materiauxService = new MateriauxServiceImpl(new MateriauxRepositoryImpl());
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
            System.out.println("3. Finaliser le Projet");
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
                    updateMargeBeneficiaireEtCoutTotal(projetId);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    ComposantMenu(projetId);
            }
        } while (choice != 4);
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
                    GestionClientTest.NouveauProjet();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez ressayer.");
                    displayallprojet();

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
        projetdetailmenu(projetId);
    }

    private static void updateMargeBeneficiaireEtCoutTotal(int projetId) {

        System.out.print("Entrez la marge bénéficiaire (%) : ");
        double margeBeneficiaire = scanner.nextDouble();
        scanner.nextLine();


        double totalMaterialCost = materiauxService.calculateTotalMaterialCost(projetId);
        double totalMaindoeuvre = mainDoeuvreService.calculeMaiOuvreTotal(projetId);
        double totalCost = totalMaterialCost + totalMaindoeuvre;

        Projet projet = new Projet(projetId, null, margeBeneficiaire, totalCost, null, null, null);


        projetService.updateProjet(projet, projetId);

        System.out.println("Projet mis à jour avec succès avec la marge bénéficiaire de " + margeBeneficiaire + "% !");
        System.out.println("Le coût total  : " + projet.getCoutTotal());

        System.out.println("Generation du devis:");
        GestionduDevisTest.createDevis(totalCost,margeBeneficiaire,projetId);

    }
    private static void updateProjectStatus(int projetId) {

        EtatProjet etatProjet = null;
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("Choisissez un statut pour le projet :");
            System.out.println("1. EnCours");
            System.out.println("2. Termine");
            System.out.println("3. Annule");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    etatProjet = EtatProjet.EnCours;
                    validChoice = true;
                    break;
                case 2:
                    etatProjet = EtatProjet.Termine;
                    validChoice = true;
                    break;
                case 3:
                    etatProjet = EtatProjet.Annule;
                    validChoice = true;
                    break;
                default:
                    System.out.println("Choix invalide, veuillez choisir 1, 2 ou 3.");
            }
        }

        Projet projet = new Projet(projetId, null, null, null, etatProjet, null, null);

        projetService.updateProjetStatus(projet, projetId);

        System.out.println("Projet mis à jour avec succès !");
        System.out.println("Nouveau statut : " + projet.getEtat_projet());
        projetdetailmenu(projetId);
    }

    public static void projetdetailmenu(int projetId){
        while (true) {
            System.out.println("=========================================");
            System.out.println("|  1. Changer le statut du projet        |");
            System.out.println("|  2. Afficher les détails du devis      |");
            System.out.println("|  3. Afficher les détails des composant |");
            System.out.println("|  4. <---- Retour                       |");
            System.out.println("=========================================");
            System.out.print("---> ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    updateProjectStatus(projetId);
                    break;

                case 2:
                        GestionduDevisTest.AffichageDevis(projetId);
                    break;
                case 3:
                    GestionDesMainDoeuvreTest.getmaindoeuvre(projetId);
                    MateriauxTest.getMateriaux(projetId);
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    displayallprojet();
                   break;

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }







}

