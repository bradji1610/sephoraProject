package com.sephora.data;

import com.sephora.data.model.Store;
import com.sephora.data.service.StoreService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeforeAYearTest {
    @Test
    public void beforeAYear() {
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
        assertEquals(2, service.filterStoresAfter(storesList, "1998").size());
    }
}
