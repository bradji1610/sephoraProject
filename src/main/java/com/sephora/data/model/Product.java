package com.sephora.data.model;

public class Product {
    private int id;
    private String nom;
    private int brandId;
    private String categorie;
    private String sousCategorie;
    private double prix;
    private String dateLancement;
    private boolean isVegan;
    private boolean isBio;

    // TODO : Constructeur avec tous les paramètres
    public Product(int id, String nom, int brandId, String categorie, String sousCategorie,
                   double prix, String dateLancement, boolean isVegan, boolean isBio) {
        this.id = id;
        this.nom = nom;
        this.brandId = brandId;
        this.categorie = categorie;
        this.sousCategorie = sousCategorie;
        this.prix = prix;
        this.dateLancement = dateLancement;
        this.isVegan = isVegan;
        this.isBio = isBio;
    }

    // TODO : Getters pour chaque champ
    public int getId() { return id; }
    public String getNom() { return nom; }
    public int getBrandId() { return brandId; }
    public String getCategorie() { return categorie; }
    public String getSousCategorie() { return sousCategorie; }
    public double getPrix() { return prix; }
    public String getDateLancement() { return dateLancement; }
    public boolean isVegan() { return isVegan; }
    public boolean isBio() { return isBio; }

    // TODO : toString() lisible
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", brandId=" + brandId +
                ", categorie='" + categorie + '\'' +
                ", sousCategorie='" + sousCategorie + '\'' +
                ", prix=" + prix +
                ", dateLancement='" + dateLancement + '\'' +
                ", isVegan=" + isVegan +
                ", isBio=" + isBio +
                '}';
    }
}
