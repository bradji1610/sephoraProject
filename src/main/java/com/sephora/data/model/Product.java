package com.sephora.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    @JsonProperty("id") private int id;
    @JsonProperty("nom") private String nom;
    @JsonProperty("brand_id") private int brandId;
    @JsonProperty("categorie") private String categorie;
    @JsonProperty("sous_categorie") private String sousCategorie;
    @JsonProperty("prix") private double prix;
    @JsonProperty("date_lancement") private String dateLancement;
    @JsonProperty("is_vegan") private boolean isVegan;
    @JsonProperty("is_bio") private boolean isBio;

    // Constructor for Jackson
    public Product() {
    }

    // Getters for Jackson
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public int getBrandId() {
        return brandId;
    }
    public String getCategorie() {
        return categorie;
    }
    public String getSousCategorie() {
        return sousCategorie;
    }
    public double getPrix() {
        return prix;
    }
    public String getDateLancement() {
        return dateLancement;
    }
    public boolean isVegan() {
        return isVegan;
    }
    public boolean isBio() {
        return isBio;
    }
}