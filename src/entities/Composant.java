package entities;

import enums.TypeComposant;

public class Composant {

        private Integer composantId;
        private String nom;
        private Double coutUnitaire;
        private Double quantite;
        private Double tauxTVA;
        private TypeComposant typeComposant;


        public Composant(Integer composantId, String nom, Double coutUnitaire, Double quantite, Double tauxTVA,
                         TypeComposant typeComposant) {
            this.composantId = composantId;
            this.nom = nom;
            this.coutUnitaire = coutUnitaire;
            this.quantite = quantite;
            this.tauxTVA = tauxTVA;
            this.typeComposant = typeComposant;

        }

        // Getters and Setters
        public Integer getComposantId() {
            return composantId;
        }

        public void setComposantId(Integer composantId) {
            this.composantId = composantId;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
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

        public void setQuantite(Double quantite) {
            this.quantite = quantite;
        }

        public Double getTauxTVA() {
            return tauxTVA;
        }

        public void setTauxTVA(Double tauxTVA) {
            this.tauxTVA = tauxTVA;
        }

        public TypeComposant getTypeComposant() {
            return typeComposant;
        }

        public void setTypeComposant(TypeComposant typeComposant) {
            this.typeComposant = typeComposant;
        }



        @Override
        public String toString() {
            return composantId +
                    ": nom='" + nom + '\'' +
                    ", coutUnitaire=" + coutUnitaire +
                    ", quantite=" + quantite +
                    ", tauxTVA=" + tauxTVA +
                    ", typeComposant=" + typeComposant ;
        }
    }


