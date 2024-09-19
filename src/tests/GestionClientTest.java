package tests;

import entities.Client;
import services.implementations.ClientServiceImpl;
import services.interfaces.ClientService;

import java.util.Optional;
import java.util.Scanner;

public class GestionClientTest {
    static final Scanner scanner = new Scanner(System.in);
    static final ClientService clientService = new ClientServiceImpl();
    public static void main(String[] args) {
        NouveauProjet();
    }
   public static void NouveauProjet() {
        System.out.println("=========================================================================");
        System.out.println("| Souhaitez-vous chercher un client existant ou en ajouter un nouveau ? |");
        System.out.println("=========================================================================");
        System.out.println("|                 1. Chercher un client existant.                       |");
        System.out.println("|                 2. Ajouter un nouveau client.                         |");
        System.out.println("=========================================================================");
        System.out.print("--> ");

        int clientChoice = scanner.nextInt();
        scanner.nextLine();

        switch (clientChoice) {
            case 1:
                rechercherNouveauClient();
                break;
            case 2:
                ajouterNouveauClient();
                break;
            default:
                System.out.println("Option invalide, veuillez réessayer.");
        }
    }

    private static void ajouterNouveauClient() {
        System.out.print("_ Le nom du client : ");
        String nom = scanner.nextLine();

        System.out.print("_ L'adresse du client : ");
        String adresse = scanner.nextLine();

        System.out.print("_ Numéro de téléphone du client (10 chiffres) : ");
        String telephone = scanner.nextLine();

        System.out.print("_ Le client est-il un professionnel ? (true/false) : ");
        boolean estProfessionnel = scanner.nextBoolean();
        scanner.nextLine();

        Client client = new Client(0,nom, adresse, telephone, estProfessionnel);
        clientService.addClient(client);
    }

    private static void rechercherNouveauClient() {
        System.out.print("_ Le nom du client : ");
        String nom = scanner.nextLine();
        Optional<Client> client = clientService.findClientByName(nom);
        client.ifPresent(
          c-> {
              int ClientId = c.getClient_id();
              System.out.println("Client trouvé !");
              System.out.println("Nom : " + c.getNom());
              System.out.println("Adresse : " + c.getAdresse());
              System.out.println("Numéro de téléphone : " + c.getTelephone());
              System.out.println("Professionnel : " + c.getEst_professionnel());
          }
        );

    }



    }
