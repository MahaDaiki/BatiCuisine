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
            Scanner scanner = new Scanner(System.in);

            System.out.print("Entrez le nom du projet: ");
            String nomProjet = scanner.nextLine();

            System.out.print("Entrez la surface du projet: ");
            double surface = scanner.nextDouble();

            Projet projet = new Projet(null, nomProjet, null, null, EtatProjet.EnCours, surface, clientId);


            projetService.addProjet(projet, clientId);
        }

        }

