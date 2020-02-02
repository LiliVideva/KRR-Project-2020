package basic;

import model.Model;
import node.Node;

import java.util.Objects;

public abstract class RDF_Resource {
    private Node node;
    private Model model;

    public RDF_Resource(Node node, Model model) {
        this.node = node;
        this.model = model;
    }

    public Node asNode() {
        return node;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public boolean isLiteral() {
        return node.isLiteral();
    }

    public boolean isResource() {
        return node.isURI();
    }

    public abstract RDF_Resource inModel(Model m);

    public abstract RDFS_Class asResource();

    public abstract RDFS_Literal asLiteral();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RDF_Resource that = (RDF_Resource) o;
        return Objects.equals(node, that.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node);
    }

    @Override
    public String toString() {
        return "\"rdf:resource\": {\n" + asNode().toString() + "\n}";
    }
}
