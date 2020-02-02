package element;

import basic.RDFS_Class;
import basic.RDF_Property;
import basic.RDF_Resource;
import model.Model;
import node.Node;

public class RDF_Predicate extends RDF_Property {
    public RDF_Predicate() {
        super();
    }

    public RDF_Predicate(String uri) {
        super(uri);
    }

    @Override
    public RDF_Property inModel(Model model) {
        return getModel() == model ? this : model.createProperty(getURI());
    }

    @Override
    public String toString() {
        return "\"rdf:predicate\": {\n" + asNode().toString() + "\n}";
    }

    public RDF_Predicate(String nameSpace, String localName) {
        super(nameSpace, localName);
    }

    public RDF_Predicate(String uri, Model model) {
        super(uri, model);
    }

    public RDF_Predicate(String nameSpace, String localName, Model model) {
        super(nameSpace, localName, model);
    }

    public RDF_Predicate(Node node, Model model) {
        super(node, model);
    }

    @Override
    protected boolean checkDomain(RDFS_Class resource) {
        return resource instanceof RDF_Statement;
    }

    @Override
    protected boolean checkRange(RDF_Resource resource) {
        return true;
    }
}
