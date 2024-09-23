package entities;

import enums.TypeComposant;

public class MainDoeuvre extends Composant {
    private Double tauxHoraire;
    private Double heuresTravail;
    private Double productiviteOuvrier;



    public MainDoeuvre(Integer composantId, String nom, Double tauxTVA,
                       Double tauxHoraire, Double heuresTravail, Double productiviteOuvrier, Integer projetId) {
        super(composantId, nom,  tauxTVA,TypeComposant.Main_doeuvre, projetId);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public Double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(Double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public Double getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(Double heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public Double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(Double productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }

    @Override
    public String toString() {
        return "MainDoeuvre{" +
                "composantId=" + getComposantId() +
                ", nom='" + getNom() + '\'' +
                ", tauxTVA=" + getTauxTVA() +
                ", typeComposant=" + getTypeComposant() +
                ", tauxHoraire=" + tauxHoraire +
                ", heuresTravail=" + heuresTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                '}';
    }
}
