package com.sephora.data.service;

import com.sephora.data.model.Material;

import java.util.*;
import java.util.stream.Collectors;

public class MaterialService {

    // Materials per certification
    public Map<String, Long> countByCertification(List<Material> materials) {
        return materials.stream()
                .collect(Collectors.groupingBy(
                        m -> {
                            String cert = m.getCertification();
                            if (cert == null || cert.isEmpty()) return "none";
                            return cert.toLowerCase();
                        },
                        Collectors.counting()
                ));
    }

    // 5 most used materials per appearance
    public List<Map.Entry<String, Long>> top5Materials(List<Material> materials) {
        return materials.stream()
                .collect(Collectors.groupingBy(
                        Material::getMatiere,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    // Materials sorted by percentage
    public List<Material> sortByPercentageDesc(List<Material> materials) {
        return materials.stream()
                .sorted(Comparator.comparing(Material::getPourcentage).reversed())
                .collect(Collectors.toList());
    }

    public List<Map.Entry<String, Long>> top5SuppliersByComponents(List<Material> materials) {
        return materials.stream()
                .collect(Collectors.groupingBy(Material::getFournisseur, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());
    }
}
