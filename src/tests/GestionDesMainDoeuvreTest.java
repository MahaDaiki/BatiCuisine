package tests;

import entities.MainDoeuvre;
import services.implementations.MainDoeuvreServiceImpl;
import services.interfaces.MainDoeuvreService;

import java.util.Scanner;

public class GestionDesMainDoeuvreTest {
    static final Scanner scanner = new Scanner(System.in);
    static final MainDoeuvreService maindoeuvre = new MainDoeuvreServiceImpl();

    public static void main(String[] arg){

    }

    public static void AddMainDoeuvre(int projetId){

        String response;
        do {
            System.out.println("*--- Main-d'œuvre ---*");

            System.out.print("Entrez main-d'œuvre : ");
            String nom = scanner.nextLine();

            System.out.print("Entrez le taux horaire de cette main-d'œuvre (dh/h) : ");
            double tauxHoraire = scanner.nextDouble();

            System.out.print("Entrez le nombre d'heures  de travail : ");
            double heuresTravaillees = scanner.nextDouble();

            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            double productivite = scanner.nextDouble();

            System.out.print("Entrez le Taux TVA : ");
            double tauxTVA = scanner.nextDouble();

            scanner.nextLine();


            MainDoeuvre mainDoeuvre = new MainDoeuvre(
                    null,
                    nom,
                    tauxHoraire,
                    heuresTravaillees,
                    tauxTVA,
                    tauxHoraire,
                    heuresTravaillees,
                    productivite,
                    projetId
            );


            maindoeuvre.addMainDoeuvre(mainDoeuvre, projetId);

            System.out.println("Main-d'œuvre ajoutée avec succès !");

            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
               response = scanner.nextLine();

        } while (response.equalsIgnoreCase("y" ) || response.equalsIgnoreCase("yes"));

        System.out.println("Retour au menu principal.");
        GestionClientTest.NouveauProjet();

    }
}


