package tests;

import enums.TypeComposant;
import repositories.implementations.MateriauxRepositoryImpl;
import services.implementations.MateriauxServiceImpl;
import services.interfaces.MateriauxService;
import entities.Materiaux;
import utils.InputValidator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MateriauxTest {
    static final Scanner scanner = new Scanner(System.in);
    static final MateriauxService Materiaux = new MateriauxServiceImpl(new MateriauxRepositoryImpl());



    public static void AddMateriaux(int projetId) {

        String response;
        do {
            System.out.println("*--- Ajout des matériaux ---*");

            System.out.println("Entrez le nom du matériau : ");
            String nom = scanner.nextLine();
            while (!InputValidator.validateString(nom)) {
                System.out.print("Nom invalide. Veuillez entrer un nom valide : ");
                nom = scanner.nextLine();
            }

            double quantite;
            while (true) {
                System.out.print("Entrez la quantité de ce matériau : ");
                String inputQuantite = scanner.nextLine();
                if (InputValidator.validateDouble(inputQuantite)) {
                    quantite = Double.parseDouble(inputQuantite);
                    break;
                } else {
                    System.out.print("Quantité invalide. Veuillez entrer un nombre valide : ");
                }
            }

            double coutUnitaire;
            while (true) {
                System.out.print("Entrez le coût unitaire de ce matériau (Dh) : ");
                String inputCoutUnitaire = scanner.nextLine();
                if (InputValidator.validateDouble(inputCoutUnitaire)) {
                    coutUnitaire = Double.parseDouble(inputCoutUnitaire);
                    break;
                } else {
                    System.out.print("Coût unitaire invalide. Veuillez entrer un nombre valide : ");
                }
            }


            double coutTransport;
            while (true) {
                System.out.print("Entrez le coût de transport de ce matériau (Dh) : ");
                String inputCoutTransport = scanner.nextLine();
                if (InputValidator.validateDouble(inputCoutTransport)) {
                    coutTransport = Double.parseDouble(inputCoutTransport);
                    break;
                } else {
                    System.out.print("Coût de transport invalide. Veuillez entrer un nombre valide : ");
                }
            }


            double coefficientQualite;
            while (true) {
                System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
                String inputCoefficientQualite = scanner.nextLine();
                if (InputValidator.validateDouble(inputCoefficientQualite)) {
                    coefficientQualite = Double.parseDouble(inputCoefficientQualite);
                    break;
                } else {
                    System.out.print("Coefficient de qualité invalide. Veuillez entrer un nombre valide : ");
                }
            }

            double tauxTVA;
            while (true) {
                System.out.print("Entrez le taux TVA %: ");
                String inputTauxTVA = scanner.nextLine();
                if (InputValidator.validateDouble(inputTauxTVA)) {
                    tauxTVA = Double.parseDouble(inputTauxTVA);
                    break;
                } else {
                    System.out.print("Taux TVA invalide. Veuillez entrer un nombre valide : ");
                }
            }



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

    public static void getMateriaux(int projetId) {
        Optional<List<Materiaux>> materiauxList = Materiaux.getMateriauxByProjetId(projetId);
        if (materiauxList.isPresent()) {
            System.out.println("==== Matériaux ====");
            materiauxList.get().forEach(materiaux -> System.out.println(materiaux.toString()));
        } else {
            System.out.println("Aucun matériaux trouvé pour le projet ID: " + projetId);
        }
    }

}
