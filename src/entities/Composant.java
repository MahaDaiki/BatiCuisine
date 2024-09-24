package entities;

import enums.TypeComposant;

public class Composant {

        private Integer composantId;
        private String nom;
        private Double tauxTVA;
        private TypeComposant typeComposant;
        private Integer ProjetId;


        public Composant(Integer composantId, String nom, Double tauxTVA,
                         TypeComposant typeComposant, Integer ProjetId) {
            this.composantId = composantId;
            this.nom = nom;
            this.tauxTVA = tauxTVA;
            this.typeComposant = typeComposant;
            this.ProjetId = ProjetId;

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

        public Integer getProjetId() {
            return ProjetId;
        }

        public void setProjetId(Integer projetId) {
            ProjetId = projetId;
        }

        @Override
        public String toString() {
            return "=====================================\n" +
                    "| Composant ID: " + composantId + "\n" +
                    "| Nom: " + nom + "\n" +
                    "| Taux TVA: " + tauxTVA + "\n" +
                    "| Type Composant: " + typeComposant + "\n" +
                    "| Projet ID: " + ProjetId + "\n" +
                    "=====================================";
        }


}


