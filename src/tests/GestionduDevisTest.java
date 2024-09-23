package tests;

import entities.Devis;
import services.implementations.DevisServiceImpl;
import services.interfaces.DevisService;

import java.time.LocalDate;
import java.util.Scanner;

public class GestionduDevisTest {
    static final Scanner scanner = new Scanner(System.in);
    static final DevisService devisService = new DevisServiceImpl();
    public static void main(String[] args) {

    }

    public static void createDevis(double totalPrice, double margeBeneficiaire, int projetId){

        LocalDate dateEmission = LocalDate.now();

        LocalDate dateValidite = null;
        boolean isValidDate = false;

        while (!isValidDate) {
            System.out.println("Entrez la date de validité (YYYY-MM-DD) : ");
            String dateValiditeInput = scanner.nextLine();

            try {
                dateValidite = LocalDate.parse(dateValiditeInput);

                if (dateValidite.isAfter(dateEmission)) {
                    isValidDate = true;
                } else {
                    System.out.println("La date de validité doit être après la date d'aujourd'hui. Veuillez réessayer.");
                }
            } catch (Exception e) {
                System.out.println("Format de date invalide. Veuillez réessayer avec le format correct (YYYY-MM-DD).");
            }
        }

        double montantEstime = totalPrice + margeBeneficiaire;
        System.out.println("Votre Montant Estime est: " + montantEstime);

        System.out.println("Validez-vous ce devis ? (yes/no) ");
        String acceptInput = scanner.next().toLowerCase();
        scanner.nextLine();


        boolean accept = acceptInput.equals("yes");


        Devis devis = new Devis(null, montantEstime, dateEmission, LocalDate.now(), accept, projetId); // Using LocalDate.now() for now


        devisService.addDevis(devis);

        MenusTest.menu();

    }
}
