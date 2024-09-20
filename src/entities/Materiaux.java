package entities;

import enums.TypeComposant;



public class Materiaux extends Composant{
    private Double coefficientQualite;
    private Double coutTransport;
    public Materiaux(Integer composantId, String nom, Double coutUnitaire, Double quantite, Double tauxTVA, TypeComposant typeComposant, Integer projetId, Double coefficientQualite, Double coutTransport) {
        super(composantId, nom, coutUnitaire, quantite, tauxTVA,TypeComposant.Matériel, projetId );
        this.coefficientQualite = coefficientQualite;
        this.coutTransport = coutTransport;
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

    @Override
    public String toString() {
        return "Materiaux:" + getComposantId() +
                "_" + getNom() + '\'' +
                ", coutUnitaire=" + getCoutUnitaire() +
                ", quantite=" + getQuantite() +
                ", tauxTVA=" + getTauxTVA() +
                ", coefficientQualite=" + coefficientQualite +
                ", coutTransport=" + coutTransport ;
    }
}
