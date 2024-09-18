package entities;
import enums.EtatProjet;


public class Projet {

        private Integer projetId;
        private String nomProjet;
        private Double margeBeneficiaire;
        private Double coutTotal;
        private EtatProjet etatProjet;
        private Integer clientId;
        private Integer componentId;


        public Projet(Integer projetId, String nomProjet, Double margeBeneficiaire, Double coutTotal,
                       EtatProjet etatProjet, Integer clientId, Integer componentId) {
            this.projetId = projetId;
            this.nomProjet = nomProjet;
            this.margeBeneficiaire = margeBeneficiaire;
            this.coutTotal = coutTotal;
            this.etatProjet = etatProjet;
            this.clientId = clientId;
            this.componentId = componentId;
        }

        // Getters and Setters
        public Integer getProjetId() {
            return projetId;
        }

        public void setProjetId(Integer projetId) {
            this.projetId = projetId;
        }

        public String getNomProjet() {
            return nomProjet;
        }

        public void setNomProjet(String nomProjet) {
            this.nomProjet = nomProjet;
        }

        public Double getMargeBeneficiaire() {
            return margeBeneficiaire;
        }

        public void setMargeBeneficiaire(Double margeBeneficiaire) {
            this.margeBeneficiaire = margeBeneficiaire;
        }

        public Double getCoutTotal() {
            return coutTotal;
        }

        public void setCoutTotal(Double coutTotal) {
            this.coutTotal = coutTotal;
        }

        public EtatProjet getEtatProjet() {
            return etatProjet;
        }

        public void setEtatProjet(EtatProjet etatProjet) {
            this.etatProjet = etatProjet;
        }

        public Integer getClientId() {
            return clientId;
        }

        public void setClientId(Integer clientId) {
            this.clientId = clientId;
        }

        public Integer getComponentId() {
            return componentId;
        }

        public void setComponentId(Integer componentId) {
            this.componentId = componentId;
        }

        @Override
        public String toString() {
            return   projetId +
                    "_ Nom du Projet='" + nomProjet + '\'' +
                    ", margeBeneficiaire=" + margeBeneficiaire +
                    ", coutTotal=" + coutTotal +
                    ", etatProjet=" + etatProjet +
                    ", clientId=" + clientId +
                    ", componentId=" + componentId ;
        }
    }


