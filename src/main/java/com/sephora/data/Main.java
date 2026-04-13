package com.sephora.data;

import com.sephora.data.model.Material;
import com.sephora.data.model.Product;
import com.sephora.data.model.Store;
import com.sephora.data.parser.XmlMaterialParser;
import com.sephora.data.service.MaterialService;
import com.sephora.data.service.StoreService;
import com.sephora.data.parser.JsonProductParser;
import com.sephora.data.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/data/stores_full.csv";
        StoreService service = new StoreService();

        List<Store> stores = new ArrayList<>();
        try {
            stores = service.loadStoresFromCsv(filePath);;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // === Store per country ===
        System.out.println();
        System.out.println("=== Magasins par pays ===");
        Map<String, Integer> counts = service.countStoresByCountry(stores);
        counts.forEach((pays, count) -> System.out.println(pays + " : " + count + " magasins"));
        System.out.println();

        // === Oldest store ===
        System.out.println("=== Magasins le plus ancien ===");
        Store oldestStore = service.findOldestStore(stores);
        if (oldestStore != null) {
            System.out.println(oldestStore.getNom() + " (ouvert le " + oldestStore.getDateOuverture() + ")");
        }
        System.out.println();

        // === Stores open after 2020 ===
        System.out.println("=== Magasins ouverts après 2020 ===");
        List<Store> storesAfter2020 = service.filterStoresAfter(stores, "2020");
        System.out.println(storesAfter2020.size() + " magasins trouvés");

        // === Top 5 regions per store count ===
        System.out.println("=== Top 5 par région ===");
        List<Map.Entry<String, Long>> top = service.top5Regions(stores);
        for (Map.Entry<String, Long> entry : top) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " magasins");
        }
        System.out.println();

        // === Total surface per region ===
        System.out.println("=== Surface Totale par région ===");
        Map<String, Integer> surfaceByCountry = service.totalSurfaceByCountry(stores);
        surfaceByCountry.forEach((pays, surface) -> System.out.println(pays + " ," + "surface : " + surface));
        System.out.println();

        // === Store under renovation sorted by surface (desc) ===
        System.out.println("=== Magasins en renovation triés par superficie  ===");
        List<Store> renovationStores = service.getRenovationStoresSorted(stores);
        for (Store store : renovationStores) {
            System.out.println(store.getNom() + " - " + store.getSurface() + " m²");
        }

        String productFilePath = "src/main/resources/data/products_full.json";
        ProductService productService = new ProductService();
        JsonProductParser jsonParser = new JsonProductParser();

        List<Product> products = new ArrayList<>();
        try {
            products = jsonParser.parse(productFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // === Number of product per category ===
        System.out.println();
        System.out.println("=== Nombre de produits par catégorie ===");
        Map<String, Long> productPerCategory = productService.countProductsByCategory(products);
        productPerCategory.forEach((product, category) -> System.out.println(product + " : " + category));
        System.out.println();

        // === Number of vegan product per brand ===
        System.out.println("=== Nombre de produits vegan par marque ===");
        Map<Integer, Long> veganProductPerBrand = productService.countVeganProductsByBrand(products);
        veganProductPerBrand.forEach((count, brand) -> System.out.println(brand + " : " + count));
        System.out.println();

        // === Average price per category ===
        System.out.println("=== Prix moyen par catégorie");
        Map<String, Double> averagePricePerCategory = productService.averagePriceByCategory(products);
        averagePricePerCategory.forEach((category, averagePrice) -> System.out.println("category "+category + " : " + averagePrice));
        System.out.println();

        // === 10 most expensive product ===
        System.out.println("=== Top 10 des produits les plus chers ===");
        Map<String, Double> expensiveProduct = productService.top10MostExpensiveProducts(products);
        expensiveProduct.forEach((product, price) -> System.out.println( product + " : " + price));
        System.out.println();

        String materialFilePath = "src/main/resources/data/materials_full.xml";
        MaterialService materialService = new MaterialService();
        XmlMaterialParser materialParser = new XmlMaterialParser();

        List<Material> materials = new ArrayList<>();
        try {
            materials = XmlMaterialParser.parse(materialFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // === Materials per certification ===
        System.out.println("=== Nombre de composants bio vs vegan vs cruelty-free vs none");
        Map<String, Long> componentPerCertification = materialService.countByCertification(materials);
        componentPerCertification.forEach((componant, count) -> System.out.println( componant + " : " + count));
        System.out.println();

        // === Most used materials  ===
        System.out.println("=== Top 5 des matières les plus utilisées  ===");
        List<Map.Entry<String, Long>> topMatarial = materialService.top5Materials(materials);
        for (Map.Entry<String, Long> entry : topMatarial) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();

        // === Most used materials  ===
        System.out.println("=== Top 5 des fournisseurs par nombre de composants fournis  ===");
        List<Map.Entry<String, Long>> topSupplier = materialService.top5SuppliersByComponents(materials);
        for (Map.Entry<String, Long> entry : topSupplier) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
    }

}
