package com.sephora.data.parser;

import com.sephora.data.model.Material;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlMaterialParser {

    public static List<Material> parse(String filePath) {

        List<Material> materials = new ArrayList<>();

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filePath));

            Material currentMaterial = null;

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {

                    String tagName = reader.getLocalName();

                    if ("material".equals(tagName)) {
                        currentMaterial = new Material();
                    }

                    if (currentMaterial != null) {
                        switch (tagName) {
                            case "id":
                                currentMaterial.setId(Integer.parseInt(reader.getElementText()));
                                break;
                            case "product_id":
                                currentMaterial.setProductId(Integer.parseInt(reader.getElementText()));
                                break;
                            case "matiere":
                                currentMaterial.setMatiere(reader.getElementText());
                                break;
                            case "fournisseur":
                                currentMaterial.setFournisseur(reader.getElementText());
                                break;
                            case "pourcentage":
                                currentMaterial.setPourcentage(Integer.parseInt(reader.getElementText()));
                                break;
                            case "certification":
                                currentMaterial.setCertification(reader.getElementText());
                                break;
                        }
                    }
                }

                if (event == XMLStreamConstants.END_ELEMENT) {
                    if ("material".equals(reader.getLocalName())) {
                        materials.add(currentMaterial);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return materials;
    }
}
