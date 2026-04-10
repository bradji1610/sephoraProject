package com.sephora.data.service;

import com.sephora.data.model.Store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StoreService {

    // Lecture du CSV
    public List<Store> loadStoresFromCsv(String filePath) {
        List<Store> stores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Supprimer les guillemets éventuels
                line = line.replace("\"", "");

                // Séparer les champs
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // Assure que la taille de chaque ligne soit de 9 ( 9 éléments pour 9 colonnes)
                if (parts.length < 9)
                    continue;

                try {
                    Store store = new Store(
                            Integer.parseInt(parts[0].trim()),   // id
                            parts[1].trim(),                     // nom
                            parts[2].trim(),                     // adresse
                            parts[3].trim(),                     // pays
                            parts[4].trim(),                     // region
                            Integer.parseInt(parts[5].trim()),   // surface
                            parts[6].trim(),                     // date_ouverture
                            parts[7].trim(),                     // statut
                            Integer.parseInt(parts[8].trim())    // nb_employes
                    );
                    stores.add(store);
                } catch (Exception e) {
                    System.err.println("Erreur parsing ligne : " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stores;
    }

    // Comptage des magasins groupés pays
    public Map<String, Integer> countStoresByCountry(List<Store> stores) {
        Map<String, Integer> map = new TreeMap<>(); // tri alphabétique
        for (Store s : stores) {
            String country = s.getPays();
            map.put(country, map.getOrDefault(country, 0) + 1);
        }
        return map;
    }

    // Magasin le plus ancien
    public Store findOldestStore(List<Store> stores) {
        return stores.stream()
                .min(Comparator.comparing(Store::getDateOuverture))
                .orElse(null);

        // test pour plus tard , utiliser un Optional
    }

    // Filtrer les magasins ouverts après une année donnée
    public List<Store> filterStoresAfter(List<Store> stores, String year) {
        int threshold = Integer.parseInt(year);
        return stores.stream()
                .filter(s -> {
                    try {
                        int openYear = Integer.parseInt(s.getDateOuverture().substring(0, 4));
                        return openYear > threshold;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }
}
