package com.sephora.data;

import com.sephora.data.model.Store;
import com.sephora.data.service.StoreService;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfStorePerCountry {
    @Test
    public void numberOfStorePerCountry() {
        List<Store> storesList = Arrays.asList(
                new Store(1, "Sephora Paris Champs-Elysees",
                        "70 Av. des Champs-Elysees", "FR", "Ile-de-France",
                        450, "2005-03-15", "OUVERT", 65),

                new Store(2, "Sephora Lyon Part-Dieu",
                        "17 Rue Dr Bouchut", "FR", "Auvergne-Rhone-Alpes",
                        300, "2010-06-20", "OUVERT", 40),

                new Store(62, "Sephora Milano Duomo",
                        "250 Passage des Arts", "IT", "Lombardia",
                        633, "1998-05-26", "OUVERT", 61
                )
        );
        StoreService service = new StoreService();
        Map<String, Integer> contryMap = new HashMap<>();
        contryMap.put("FR", 2);
        contryMap.put("IT", 1);

        assertEquals(contryMap, service.countStoresByCountry(storesList));
    }

}
