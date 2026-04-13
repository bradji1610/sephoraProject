package com.sephora.data.model;

public class Material {

    private int id;
    private int productId;
    private String matiere;
    private String origine;
    private String fournisseur;
    private int pourcentage;
    private String certification;

    // Constructor for XML
    public Material() {}

    // Setters
    public void setId(int id) { this.id = id; }
    public void setProductId(int productId) { this.productId = productId; }
    public void setMatiere(String matiere) { this.matiere = matiere; }
    public void setOrigine(String origine) { this.origine = origine; }
    public void setFournisseur(String fournisseur) { this.fournisseur = fournisseur; }
    public void setPourcentage(int pourcentage) { this.pourcentage = pourcentage; }
    public void setCertification(String certification) { this.certification = certification; }

    // Getters
    public int getId() { return id; }
    public int getProductId() { return productId; }
    public String getMatiere() { return matiere; }
    public String getOrigine() { return origine; }
    public String getFournisseur() { return fournisseur; }
    public int getPourcentage() { return pourcentage; }
    public String getCertification() { return certification; }
}
