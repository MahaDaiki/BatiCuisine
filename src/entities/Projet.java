package entities;
import enums.EtatProjet;


public class Projet {

        private Integer projetId;
        private String nomProjet;
        private Double margeBeneficiaire;
        private Double coutTotal;
        private EtatProjet etatProjet;
        private Double surface;
        private Integer clientId;



        public Projet(Integer projetId, String nomProjet, Double margeBeneficiaire, Double coutTotal,
                       EtatProjet etatProjet,Double surface ,Integer clientId) {
            this.projetId = projetId;
            this.nomProjet = nomProjet;
            this.margeBeneficiaire = margeBeneficiaire;
            this.coutTotal = coutTotal;
            this.etatProjet = etatProjet;
            this.surface = surface;
            this.clientId = clientId;
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

        public Double getSurface() {
            return surface;
        }

        public void setSurface(Double surface) {
            this.surface = surface;
        }

        @Override
        public String toString() {
            return   projetId +
                    "_ Nom du Projet='" + nomProjet + '\'' +
                    ", margeBeneficiaire=" + margeBeneficiaire +", surface=" + surface +
                    ", coutTotal=" + coutTotal +
                    ", etatProjet=" + etatProjet +
                    ", clientId=" + clientId
                    ;
        }


}


