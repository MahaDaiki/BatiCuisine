package entities;

import enums.TypeComposant;



public class Materiaux extends Composant{
    private Double coefficientQualite;
    private Double coutTransport;
    private Double coutUnitaire;
    private Double quantite;

    public Materiaux(Integer composantId, String nom, Double coutUnitaire, Double quantite, Double tauxTVA, Integer projetId, Double coefficientQualite, Double coutTransport) {
        super(composantId, nom,  tauxTVA,TypeComposant.Matériel, projetId );
        this.coefficientQualite = coefficientQualite;
        this.coutTransport = coutTransport;
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
    }

    public Double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(Double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    public Double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(Double coutTransport) {
        this.coutTransport = coutTransport;
    }
    public Double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(Double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public Double getQuantite() {
        return quantite;
    }
    @Override
    public String toString() {
        return "=====================================\n" +
                "| Matériaux ID: " + getComposantId() + "\n" +
                "| Nom: " + getNom() + "\n" +
                "| Coût Unitaire: " + getCoutUnitaire() + "\n" +
                "| Quantité: " + getQuantite() + "\n" +
                "| Taux TVA: " + getTauxTVA() + "\n" +
                "| Coefficient Qualité: " + coefficientQualite + "\n" +
                "| Coût Transport: " + coutTransport + "\n" +
                "=====================================";
    }


}
