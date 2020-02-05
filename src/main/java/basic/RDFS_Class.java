package basic;

import element.RDF_Statement;
import model.Model;
import node.Node;
import node.UriNode;

import java.util.List;

public class RDFS_Class extends RDF_Resource {
    public RDFS_Class(Node node, Model model) {
        super(node, model);
    }

    public RDFS_Class() {
        this((Model) null);
    }

    public RDFS_Class(Model model) {
        this(new UriNode(null), model);
    }

    public RDFS_Class(String uri) {
        super(new UriNode(uri), null);
    }

    public RDFS_Class(String nameSpace, String localName) {
        super(new UriNode(nameSpace + localName), null);
    }

    public RDFS_Class(String uri, Model model) {
        this(new UriNode(uri), model);
    }

    public RDFS_Class(RDFS_Class resource, Model model) {
        this(resource.asNode(), model);
    }

    public RDFS_Class(String nameSpace, String localName, Model model) {
        this(new UriNode(nameSpace + localName), model);
    }

    @Override
    public RDFS_Class inModel(Model model) {
        return getModel() == model ? this
                : model.createResource(getURI());
    }

    public String getURI() {
        return asNode().getURI();
    }

    public String getNameSpace() {
        return asNode().getNameSpace();
    }

    public String getLocalName() {
        return asNode().getLocalName();
    }

    @Override
    public RDFS_Class asResource() {
        return this;
    }

    @Override
    public RDFS_Literal asLiteral() {
        return null;
    }

    @Override
    public String toString() {
        return "\"rdfs:class\": {\n" + asNode().toString() + "\n}";
    }

    public RDF_Statement getStatement(RDF_Property property) {
        return (getModel() == null) ? null : getModel().getStatement(this, property);
    }

    public List<RDF_Statement> listStatements() {
        return (getModel() == null) ? null : getModel().listStatements(this);
    }

    public RDFS_Class addProperty(RDF_Property property, Object value) {
        RDFS_Literal literal = getModel().createLiteral(value);
        if (getModel() != null) {
            if (property.checkDomain(this) && property.checkRange(literal)) {
                setModel(getModel().add(this, property, literal));
            } else {
                System.out.println("Domain and/or range restrictions don't pass.");
            }
        }
        return this;
    }

    public RDFS_Class addProperty(RDF_Property property, RDFS_Literal literal) {
        if (getModel() != null) {
            if (property.checkDomain(this) && property.checkRange(literal)) {
                setModel(getModel().add(this, property, literal));
            } else {
                System.out.println("Domain and/or range restrictions don't pass.");
            }
        }
        return this;
    }

    public RDFS_Class addProperty(RDF_Property property, RDF_Resource resource) {
        if (getModel() != null) {
            if (property.checkDomain(this) && property.checkRange(resource)) {
                setModel(getModel().add(this, property, resource));
            } else {
                System.out.println("Domain and/or range restrictions don't pass.");
            }
        }
        return this;
    }
}
