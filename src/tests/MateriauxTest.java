package tests;

import enums.TypeComposant;
import services.implementations.MateriauxServiceImpl;
import services.interfaces.MateriauxService;
import entities.Materiaux;

import java.util.Scanner;

public class MateriauxTest {
    static final Scanner scanner = new Scanner(System.in);
    static final MateriauxService Materiaux = new MateriauxServiceImpl();

    public static void main(String[] args) {

    }

    public static void AddMateriaux(int projetId) {

        String response;
        do {
            System.out.println("*--- Ajout des matériaux ---*");

            System.out.println("Entrez le nom du matériau : ");
            String nom = scanner.nextLine();

            System.out.println("Entrez la quantité de ce matériau  : ");
            double quantite = scanner.nextDouble();

            System.out.println("Entrez le coût unitaire de ce matériau Dh : ");
            double coutUnitaire = scanner.nextDouble();

            System.out.println("Entrez le coût de transport de ce matériau (Dh) : ");
            double coutTransport = scanner.nextDouble();

            System.out.println("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            double coefficientQualite = scanner.nextDouble();

            System.out.println("Entrez le taux TVA : ");
            double tauxTVA = scanner.nextDouble();


            scanner.nextLine();


            Materiaux materiaux = new Materiaux(
                    null,
                    nom,
                    coutUnitaire,
                    quantite,
                    tauxTVA,
                    projetId,
                    coefficientQualite,
                    coutTransport


            );


            Materiaux.addMateriaux(materiaux, projetId);



            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            response = scanner.nextLine();

        } while (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes"));

        System.out.println("<----- Retour.");
        GestionProjetTest.lastinsertedprojet();
    }

}
