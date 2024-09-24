package entities;

public class Client {
    private int Client_id;
    private String nom;
    private String adresse;
    private String telephone;
    private boolean est_professionnel;

    public Client(int Client_id, String nom, String adresse, String telephone, boolean est_professionnel) {
        this.Client_id = Client_id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.est_professionnel = est_professionnel;
    }

    public int getClient_id() {
        return Client_id;
    }

    public void setClient_id(int client_id) {
        Client_id = client_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean getEst_professionnel() {
        return est_professionnel;
    }

    public void setEst_professionnel(boolean est_professionnel) {

        this.est_professionnel = est_professionnel;
    }
    public String toString() {
        return "=====================================\n" +
                "| Client ID: " + Client_id + "\n" +
                "| Nom: " + nom + "\n" +
                "| Adresse: " + adresse + "\n" +
                "| Téléphone: " + telephone + "\n" +
                "| Est Professionnel: " + est_professionnel + "\n" +
                "=====================================";
    }
}
