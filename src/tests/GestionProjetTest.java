package tests;

import entities.Projet;
import enums.EtatProjet;
import services.implementations.ClientServiceImpl;
import services.implementations.ProjetServiceImpl;
import services.interfaces.ClientService;
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

            int projetId = projetService.getLastInsertedProjetId();

            if (projetId != -1) {
                System.out.println("Projet ajouté avec succès avec ID: " + nomProjet);
                GestionDesMainDoeuvreTest.AddMainDoeuvre(projetId);
            } else {
                System.out.println("Erreur lors de l'obtention de l'ID du projet.");
            }
        }

        }

