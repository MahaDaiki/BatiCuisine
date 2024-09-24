package tests;

import entities.Devis;
import repositories.implementations.DevisRepositoryImpl;
import services.implementations.DevisServiceImpl;
import services.interfaces.DevisService;
import utils.InputValidator;

import java.time.LocalDate;
import java.util.Scanner;

public class GestionduDevisTest {
    static final Scanner scanner = new Scanner(System.in);
    static final DevisService devisService = new DevisServiceImpl(new DevisRepositoryImpl());


    public static void createDevis(double totalPrice, double margeBeneficiaire, int projetId){

        LocalDate dateEmission = LocalDate.now();

        LocalDate dateValidite = null;


        while (true) {
            System.out.print("Entrez la date de validité (YYYY-MM-DD) : ");
            String dateValiditeInput = scanner.nextLine();

            dateValidite = InputValidator.parseDate(dateValiditeInput);
            if (dateValidite != null && dateValidite.isAfter(dateEmission)) {
                break;
            } else if (dateValidite == null) {
                System.out.println("Format de date invalide. Veuillez réessayer avec le format correct (YYYY-MM-DD).");
            } else {
                System.out.println("La date de validité doit être après la date d'aujourd'hui. Veuillez réessayer.");
            }
        }

        double montantEstime = totalPrice + margeBeneficiaire;
        System.out.println("Votre Montant Estime est: " + montantEstime);



        boolean accept;
        while (true) {
            System.out.print("Validez-vous ce devis ? (yes/no) ");
            String acceptInput = scanner.nextLine().toLowerCase();
            if (acceptInput.equals("yes")) {
                accept = true;
                break;
            } else if (acceptInput.equals("no")) {
                accept = false;
                break;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer 'yes' ou 'no'.");
            }
        }


        Devis devis = new Devis(null, montantEstime, dateEmission, dateValidite, accept, projetId);
        devisService.addDevis(devis);

        MenusTest.menu();

    }

    static void AffichageDevis(int projet_id){
        Devis devis = devisService.getDevisByProjetId(projet_id);

        if (devis != null) {
            System.out.println("Détails du devis : " + devis);
        } else {
            System.out.println("Aucun devis trouvé pour le projet avec ID : " + projet_id);
        }

    }
}
