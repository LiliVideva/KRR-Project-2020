package model;

import basic.RDFS_Class;
import basic.RDF_Property;
import com.github.jsonldjava.utils.JsonUtils;
import element.RDF_Statement;
import org.json.simple.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();

        String globalUrl;
        Object jsonLdObject = null;
        Map<String, RDFS_Class> classList = new HashMap<>();
        Map<String, RDFS_Class> itemsList = new HashMap<>();
        Map<String, RDF_Property> propertyList = new HashMap<>();
        try (InputStream inputStream = new FileInputStream("./src/main/resources/data.jsonld")) {
            jsonLdObject = JsonUtils.fromInputStream(inputStream);
        } catch (IOException e) {
            System.err.println("Unable to read the JSON-LD file: " + e.getMessage());
        }

        JSONObject jsonObject = new JSONObject((Map) jsonLdObject);
        JSONObject context = new JSONObject((Map) jsonObject.get("@context"));
        for (Object key : context.keySet()) {
            if (key.equals("ex")) {
                globalUrl = (String) context.get(key);
                continue;
            }
            JSONObject value = new JSONObject((Map) context.get(key));
            String resource = (String) value.get("@id");
            String type = (String) value.get("@type");
            switch (type) {
                case "rdfs:Resource":
                case "rdfs:Class": {
                    RDFS_Class resourceClass = model.createResource(resource);
                    classList.put(resource, resourceClass);
                    break;
                }
                case "rdf:Property": {
                    RDF_Property property = model.createProperty(resource);
                    propertyList.put(resource, property);

                    String domain = (String) value.get("rdfs:domain");
                    property.addProperty(model.getDomain(), domain);
                    String range = (String) value.get("rdfs:range");
                    property.addProperty(model.getRange(), range);
                    break;
                }
            }
        }

        for (Object item : jsonObject.keySet()) {
            if (item.equals("@context")) {
                continue;
            }
            JSONObject content = new JSONObject((Map) jsonObject.get(item));

            String itemId = (String) content.get("@id");
            RDFS_Class resource = model.createResource(itemId);
            itemsList.put(itemId, resource);
            for (Object property : content.keySet()) {
                if (property.equals("@id") || property.equals("@type")) {
                    continue;
                }
                resource.addProperty(propertyList.get(property.toString()), content.get(property));
            }
        }
        for (RDFS_Class item : itemsList.values()) {
            for (RDF_Statement statement : model.listStatements(item)) {
                System.out.println(statement.getCompactFormat());
                for (RDF_Statement st : statement.getModel().listStatements(statement)) {
                    System.out.println(st.getCompactFormat());
                }
            }
        }
        for (RDF_Property property : propertyList.values()) {
            for (RDF_Statement statement : model.listStatements(property)) {
                System.out.println(statement.getCompactFormat());
                for (RDF_Statement st : statement.getModel().listStatements(statement)) {
                    System.out.println(st.getCompactFormat());
                }
            }
        }
    }
}
