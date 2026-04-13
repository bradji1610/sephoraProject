package com.sephora.data.service;

import com.sephora.data.model.Store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StoreService {

    // CSV read
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

                // Suppress unnecessary quotation mark
                line = line.replace("\"", "");

                // Separate fields
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // Assert every line contains 9 elements
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

    // group count per country
    public Map<String, Integer> countStoresByCountry(List<Store> stores) {
        return stores.stream()
                .collect(Collectors.groupingBy(
                        Store::getPays,
                        TreeMap::new,
                        Collectors.summingInt(s -> 1)
                ));
    }

    // Oldest Store
    public Store findOldestStore(List<Store> stores) {
        return stores.stream()
                .min(Comparator.comparing(Store::getDateOuverture))
                .orElse(null);
    }
        // To do use Optionnal

    // List of sorted stores after a year
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

    // top 5 region per region
    public List<Map.Entry<String, Long>> top5Regions(List<Store> stores) {
        return stores.stream()
                .collect(Collectors.groupingBy(
                        Store::getRegion,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    // Total surface per country
    public Map<String, Integer> totalSurfaceByCountry(List<Store> stores) {
        return stores.stream()
                .collect(Collectors.groupingBy(
                        Store::getPays,
                        Collectors.summingInt(Store::getSurface)
                ));
    }

    //List of renewed store sorted by surface (desc)
    public List<Store> getRenovationStoresSorted(List<Store> stores) {
        return stores.stream()
                .filter(s -> "RENOVATION".equals(s.getStatut()))
                .sorted(Comparator.comparing(Store::getSurface).reversed())
                .collect(Collectors.toList());
    }
}

