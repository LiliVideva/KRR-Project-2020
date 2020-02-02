package element;

import basic.RDFS_Class;
import basic.RDFS_Literal;
import basic.RDF_Property;
import basic.RDF_Resource;
import model.Model;
import node.Node;
import node.UriNode;

import java.time.LocalDate;
import java.util.Objects;

public class RDF_Statement extends RDFS_Class {
    private RDFS_Class subject;
    private RDF_Property predicate;
    private RDF_Resource object;

    public RDF_Statement(RDFS_Class subject, RDF_Property predicate, RDF_Resource object, Model model) {
        super(model);
        this.subject = subject.inModel(model);
        this.predicate = predicate.inModel(model);
        this.object = object.inModel(model);
        super.setNode(new UriNode(getCompactFormat()));
    }

    public RDF_Statement(RDFS_Class subject, RDF_Property predicate, RDF_Resource object) {
        this(subject, predicate, object, new Model());
    }

    public RDF_Statement(RDF_Statement statement) {
        this(statement.getSubject(), statement.getPredicate(), statement.getObject());
    }

    @Override
    public RDF_Statement inModel(Model model) {
        return getModel() == model ? this
                : model.createStatement(this);
    }

    private RDF_Resource createObject(Node node, Model model) {
        return node.isLiteral() ? new RDFS_Literal(node, model) : new RDFS_Class(node, model);
    }

    public RDFS_Class getSubject() {
        return subject;
    }

    public RDF_Property getPredicate() {
        return predicate;
    }

    public RDF_Resource getObject() {
        return object;
    }

    public RDF_Statement getStatementProperty(RDF_Property property) {
        return asResource().getStatement(property);
    }

    public RDFS_Class getResource() {
        return (object instanceof RDFS_Class) ? (RDFS_Class) object : null;
    }

    public RDF_Statement getProperty(RDF_Property property) {
        return getResource().getStatement(property);
    }

    public RDFS_Literal getLiteral() {
        return (object instanceof RDFS_Literal) ? (RDFS_Literal) object : null;
    }

    public RDFS_Class asResource() {
        return new RDFS_Class((Node) null, getModel());
    }

    public boolean getBoolean() {
        return getLiteral().getBoolean();
    }

    public int getInt() {
        return getLiteral().getInt();
    }

    public double getDouble() {
        return getLiteral().getDouble();
    }

    public String getString() {
        return getLiteral().getString();
    }

    public LocalDate getDate() {
        return getLiteral().getDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RDF_Statement that = (RDF_Statement) o;
        return Objects.equals(subject, that.subject) &&
                Objects.equals(predicate, that.predicate) &&
                Objects.equals(object, that.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, predicate, object);
    }

    @Override
    public String toString() {
        return "\"rdf:statement\": {\n"
                + subject.toString() + ",\n"
                + predicate.toString() + ",\n"
                + object.toString() + "\n"
                + "}";
    }

    public String getCompactFormat() {
        return "[" + subject.asNode().toString() + " " + predicate.asNode().toString() + " " + object.asNode().toString() + "]";
    }
}
