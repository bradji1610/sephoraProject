package com.sephora.data.model;

public class Brand {
    private int id;
    private String nom;
    private String groupe;
    private String categorie;
    private String datePartenariat;
    private String paysOrigine;

    // TODO : Constructeur avec tous les paramètres
    public Brand(int id, String nom, String groupe, String categorie,
                 String datePartenariat, String paysOrigine) {
        this.id = id;
        this.nom = nom;
        this.groupe = groupe;
        this.categorie = categorie;
        this.datePartenariat = datePartenariat;
        this.paysOrigine = paysOrigine;
    }

    // TODO : Getters pour chaque champ
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getGroupe() { return groupe; }
    public String getCategorie() { return categorie; }
    public String getDatePartenariat() { return datePartenariat; }
    public String getPaysOrigine() { return paysOrigine; }

    // TODO : toString() lisible
    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", groupe='" + groupe + '\'' +
                ", categorie='" + categorie + '\'' +
                ", datePartenariat='" + datePartenariat + '\'' +
                ", paysOrigine='" + paysOrigine + '\'' +
                '}';
    }
}

