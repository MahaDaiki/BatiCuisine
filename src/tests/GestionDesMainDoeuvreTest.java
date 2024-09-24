package tests;

import entities.MainDoeuvre;
import repositories.implementations.MainDoeuvreRepositoryImpl;
import services.implementations.MainDoeuvreServiceImpl;
import services.interfaces.MainDoeuvreService;
import utils.InputValidator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GestionDesMainDoeuvreTest {
    static final Scanner scanner = new Scanner(System.in);
    static final MainDoeuvreService maindoeuvre = new MainDoeuvreServiceImpl(new MainDoeuvreRepositoryImpl());


    public static void AddMainDoeuvre(int projetId){

        String response;
        do {
            System.out.println("*--- Main-d'œuvre ---*");

            System.out.print("Entrez main-d'œuvre : ");
            String nom = scanner.nextLine();
            while (!InputValidator.validateString(nom)) {
                System.out.print("Nom invalide. Veuillez entrer un nom valide : ");
                nom = scanner.nextLine();
            }

            double tauxHoraire;
            while (true) {
                System.out.print("Entrez le taux horaire de cette main-d'œuvre (dh/h) : ");
                String inputTauxHoraire = scanner.nextLine();
                if (InputValidator.validateDouble(inputTauxHoraire)) {
                    tauxHoraire = Double.parseDouble(inputTauxHoraire);
                    break;
                } else {
                    System.out.print("Taux horaire invalide. Veuillez entrer un nombre valide : ");
                }
            }

            double heuresTravaillees;
            while (true) {
                System.out.print("Entrez les heures de travail : ");
                String inputHeuresTravaillees = scanner.nextLine();
                if (InputValidator.validateDouble(inputHeuresTravaillees)) {
                    heuresTravaillees = Double.parseDouble(inputHeuresTravaillees);
                    break;
                } else {
                    System.out.print("Heures de travail invalides. Veuillez entrer un nombre valide : ");
                }
            }

            double productivite;
            while (true) {
                System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
                String inputProductivite = scanner.nextLine();
                if (InputValidator.validateDouble(inputProductivite)) {
                    productivite = Double.parseDouble(inputProductivite);
                    break;
                } else {
                    System.out.print("Facteur de productivité invalide. Veuillez entrer un nombre valide : ");
                }
            }

            double tauxTVA;
            while (true) {
                System.out.print("Entrez le Taux TVA : ");
                String inputTauxTVA = scanner.nextLine();
                if (InputValidator.validateDouble(inputTauxTVA)) {
                    tauxTVA = Double.parseDouble(inputTauxTVA);
                    break;
                } else {
                    System.out.print("Taux TVA invalide. Veuillez entrer un nombre valide : ");
                }
            }


            scanner.nextLine();


            MainDoeuvre mainDoeuvre = new MainDoeuvre(
                    null,
                    nom,
                    tauxHoraire,
                    heuresTravaillees,
                    tauxTVA,
                    productivite,
                    projetId
            );


            maindoeuvre.addMainDoeuvre(mainDoeuvre, projetId);

            System.out.println("Main-d'œuvre ajoutée avec succès !");

            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
               response = scanner.nextLine();

        } while (response.equalsIgnoreCase("y" ) || response.equalsIgnoreCase("yes"));

        System.out.println("<----- Retour.");
        GestionProjetTest.lastinsertedprojet();
    }

    public static void getmaindoeuvre(int projetId){

        Optional<List<MainDoeuvre>> mainDoeuvreList = maindoeuvre.getMainDoeuvreByProjetId(projetId);
        if (mainDoeuvreList.isPresent()) {
            System.out.println("==== Main d'Œuvre ====");
            mainDoeuvreList.get().forEach(mainDoeuvre -> System.out.println(mainDoeuvre.toString()));
        } else {
            System.out.println("Aucune main d'œuvre trouvée pour le projet ID: " + projetId);
        }
    }
}


