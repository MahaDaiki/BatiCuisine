package entities;

import java.time.LocalDate;

public class Devis {
    private Integer devisId;
    private Double montantEstime;
    private LocalDate dateEmission;
    private LocalDate dateValidite;
    private Boolean accepte;
    private Integer projetId;


    public Devis(Integer devisId, Double montantEstime, LocalDate dateEmission, LocalDate dateValidite,
                 Boolean accepte, Integer projetId) {
        this.devisId = devisId;
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
        this.projetId = projetId;
    }


    public Integer getDevisId() {
        return devisId;
    }

    public void setDevisId(Integer devisId) {
        this.devisId = devisId;
    }

    public Double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(Double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public LocalDate getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(LocalDate dateValidite) {
        this.dateValidite = dateValidite;
    }

    public Boolean getAccepte() {
        return accepte;
    }

    public void setAccepte(Boolean accepte) {
        this.accepte = accepte;
    }

    public Integer getProjetId() {
        return projetId;
    }

    public void setProjetId(Integer projetId) {
        this.projetId = projetId;
    }

    @Override
    public String toString() {
        return "=====================================\n" +
                "| Devis ID: " + devisId + "\n" +
                "| Montant Estimé: " + montantEstime + "\n" +
                "| Date d'Émission: " + dateEmission + "\n" +
                "| Date de Validité: " + dateValidite + "\n" +
                "| Accepté: " + (accepte ? "Oui" : "Non") + "\n" +
                "| Projet ID: " + projetId + "\n" +
                "=====================================";
    }

}

