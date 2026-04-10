package com.sephora.data;

import com.sephora.data.model.Store;
import com.sephora.data.service.StoreService;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/data/stores_full.csv";
        StoreService service = new StoreService();

        List<Store> stores = service.loadStoresFromCsv(filePath);

        // === Magasins par pays ===
        System.out.println();
        System.out.println("=== Magasins par pays ===");
        Map<String, Integer> counts = service.countStoresByCountry(stores);
        counts.forEach((pays, count) -> System.out.println(pays + " : " + count + " magasins"));
        System.out.println();

        // === Magasin le plus ancien ===
        System.out.println("=== Magasins le plus ancien ===");
        Store oldestStore = service.findOldestStore(stores);
        if (oldestStore != null) {
            System.out.println(oldestStore.getNom() + " (ouvert le " + oldestStore.getDateOuverture() + ")");
        }
        System.out.println();

        // === Magasins ouverts après 2020 ===
        System.out.println("=== Magasins ouverts après 2020 ===");
        List<Store> storesAfter2020 = service.filterStoresAfter(stores, "2020");
        System.out.println(storesAfter2020.size() + " magasins trouvés");
    }
}
