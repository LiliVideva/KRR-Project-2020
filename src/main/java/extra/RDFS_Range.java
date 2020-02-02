package extra;

import basic.RDFS_Class;
import basic.RDF_Property;
import basic.RDF_Resource;
import model.Model;
import node.Node;

public class RDFS_Range extends RDF_Property {
    public RDFS_Range() {
        super();
    }

    public RDFS_Range(String uri) {
        super(uri);
    }

    @Override
    public RDF_Property inModel(Model model) {
        return getModel() == model ? this : model.createProperty(getURI());
    }

    @Override
    public String toString() {
        return "\"rdfs:range\": {\n" + asNode().toString() + "\n}";
    }

    public RDFS_Range(String nameSpace, String localName) {
        super(nameSpace, localName);
    }

    public RDFS_Range(String uri, Model model) {
        super(uri, model);
    }

    public RDFS_Range(String nameSpace, String localName, Model model) {
        super(nameSpace, localName, model);
    }

    public RDFS_Range(Node node, Model model) {
        super(node, model);
    }

    @Override
    protected boolean checkDomain(RDFS_Class resource) {
        return resource instanceof RDF_Property;
    }

    @Override
    protected boolean checkRange(RDF_Resource resource) {
        return resource instanceof RDFS_Class;
    }
}
