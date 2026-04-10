package com.sephora.data;

import com.sephora.data.model.Store;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreTest {
    @Test
    public void shouldCreateStoreWithAllFields() {
        Store store = new Store(1, "Sephora Paris Champs-Elysees",
                "70 Av. des Champs-Elysees", "FR", "Ile-de-France",
                450, "2005-03-15", "OUVERT", 65);
        assertEquals(1, store.getId());
        assertEquals("FR", store.getPays());
        assertEquals("OUVERT", store.getStatut());
    }
}


