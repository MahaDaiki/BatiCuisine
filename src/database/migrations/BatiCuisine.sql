CREATE TYPE type_composant AS ENUM (
    'Matériel',
    'Main-doeuvre',
    'Main_doeuvre'
);

CREATE TYPE etat_projet AS ENUM (
    'EnCours',
    'Termine',
    'Annule'
);

CREATE TABLE clients (
    client_id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    adresse VARCHAR(255),
    telephone VARCHAR(50),
    est_professionnel BOOLEAN NOT NULL
);

CREATE TABLE projets (
    projet_id SERIAL PRIMARY KEY,
    nom_projet VARCHAR(255) NOT NULL,
    marge_beneficiaire DOUBLE PRECISION,
    cout_total DOUBLE PRECISION,
    etat_projet etat_projet NOT NULL,
    surface DOUBLE PRECISION NOT NULL,
    client_id INTEGER,
    CONSTRAINT fk_client
      FOREIGN KEY (client_id) 
      REFERENCES clients (client_id)
      ON DELETE SET NULL
);

CREATE TABLE components (
    composant_id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    taux_tva DOUBLE PRECISION,
    type_composant type_composant NOT NULL,
    projet_id INTEGER,
    CONSTRAINT fk_projet
      FOREIGN KEY (projet_id) 
      REFERENCES projets (projet_id)
      ON DELETE CASCADE
);

CREATE TABLE devis (
    devis_id SERIAL PRIMARY KEY,
    montant_estime DOUBLE PRECISION NOT NULL,
    date_emission DATE NOT NULL,
    date_validite DATE NOT NULL,
    accepte BOOLEAN NOT NULL,
    projet_id INTEGER,
    CONSTRAINT fk_projet_devis
      FOREIGN KEY (projet_id) 
      REFERENCES projets (projet_id)
      ON DELETE CASCADE
);

CREATE TABLE main_doeuvre (
    taux_horaire DOUBLE PRECISION,
    heures_travail DOUBLE PRECISION,
    productivite_ouvrier DOUBLE PRECISION
)
INHERITS (components);

CREATE TABLE materiaux (
    cout_unitaire DOUBLE PRECISION NOT NULL,
    quantite DOUBLE PRECISION NOT NULL,
    coefficient_qualite DOUBLE PRECISION,
    cout_transport DOUBLE PRECISION
)
INHERITS (components);

 
