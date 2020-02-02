package basic;

import model.Model;
import node.Node;

public class RDF_Property extends RDFS_Class {
    public RDF_Property() {
        super();
    }

    public RDF_Property(String uri) {
        super(uri);
    }

    @Override
    public RDF_Property inModel(Model model) {
        return getModel() == model ? this : model.createProperty(getURI());
    }

    @Override
    public String toString() {
        return "\"rdf:property\": {\n" + asNode().toString() + "\n}";
    }

    public RDF_Property(String nameSpace, String localName) {
        super(nameSpace, localName);
    }

    public RDF_Property(String uri, Model model) {
        super(uri, model);
    }

    public RDF_Property(String nameSpace, String localName, Model model) {
        super(nameSpace, localName, model);
    }

    public RDF_Property(Node node, Model model) {
        super(node, model);
    }

    public boolean isProperty() {
        return true;
    }

    protected boolean checkDomain(RDFS_Class resource) {
        return true;
    }

    protected boolean checkRange(RDF_Resource resource) {
        return true;
    }
}
