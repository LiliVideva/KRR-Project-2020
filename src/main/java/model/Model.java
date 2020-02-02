package model;

import basic.RDFS_Class;
import basic.RDFS_Literal;
import basic.RDF_Property;
import basic.RDF_Resource;
import element.RDF_Object;
import element.RDF_Predicate;
import element.RDF_Statement;
import element.RDF_Subject;
import extra.RDFS_Domain;
import extra.RDFS_Range;
import node.LiteralLabel;
import node.LiteralNode;
import node.Node;
import node.UriNode;

import java.util.*;

public class Model {
    private static Map<Class<?>, List<RDF_Resource>> classResourceMap = new HashMap<>();

    private List<RDF_Statement> statements;
    private RDF_Property domain;
    private RDF_Property range;
    private RDF_Property subject;
    private RDF_Property predicate;
    private RDF_Property object;

    public Model() {
        statements = new ArrayList<>();
        classResourceMap.put(RDFS_Class.class, new ArrayList<>());
        classResourceMap.put(RDFS_Literal.class, new ArrayList<>());
        classResourceMap.put(RDF_Property.class, new ArrayList<>());

        domain = createProperty("rdfs:domain");
        range = createProperty("rdfs:range");
        subject = createProperty("rdf:subject");
        predicate = createProperty("rdf:predicate");
        object = createProperty("rdf:object");
    }

    public RDF_Resource getRDFResource(Node node) {
        return node.isLiteral() ? getNodeAs(node, RDFS_Literal.class) : getNodeAs(node, RDFS_Class.class);
    }

    private <X extends RDF_Resource> X getNodeAs(Node node, Class<X> rdfsClass) {
        RDF_Resource newNode = null;
        switch (rdfsClass.toString()) {
            case "class basic.RDFS_Class": {
                newNode = new RDFS_Class(node, this);
                break;
            }
            case "class basic.RDF_Property": {
                newNode = instantiateProperty(node);
                break;
            }
            case "class basic.RDFS_Literal": {
                newNode = new RDFS_Literal(node, this);
                break;
            }
        }

        List<RDF_Resource> resources = classResourceMap.get(rdfsClass);
        resources.add(newNode);
        classResourceMap.replace(rdfsClass, resources);

        return rdfsClass.cast(newNode);
    }

    private RDF_Resource instantiateProperty(Node node) {
        switch (node.getURI()) {
            case "rdfs:domain":
                return new RDFS_Domain(node, this);
            case "rdfs:range":
                return new RDFS_Range(node, this);
            case "rdf:subject":
                return new RDF_Subject(node, this);
            case "rdf:predicate":
                return new RDF_Predicate(node, this);
            case "rdf:object":
                return new RDF_Object(node, this);
            default:
                return new RDF_Property(node, this);
        }
    }

    public RDF_Property createProperty(String uri) {
        return getNodeAs(new UriNode(uri), RDF_Property.class);
    }

    public RDF_Statement getStatement(RDFS_Class resource, RDF_Property property) {
        for (RDF_Statement statement : statements) {
            if (statement.getSubject().equals(resource) && statement.getPredicate().equals(property)) {
                return statement;
            }
        }
        return null;
    }

    public List<RDF_Statement> listStatements(RDFS_Class resource) {
        List<RDF_Statement> resourceStatements = new ArrayList<>();

        for (RDF_Statement statement : statements) {
            if (statement.getSubject().equals(resource)) {
                resourceStatements.add(statement);
            }
        }
        return resourceStatements;
    }

    public RDFS_Literal createLiteral(Object value) {
        LiteralLabel literalLabel = new LiteralLabel(value);
        return new RDFS_Literal(new LiteralNode(literalLabel), this);
    }

    public Model add(RDFS_Class resource, RDF_Property property, RDF_Resource literal) {
        RDF_Statement statement = new RDF_Statement(resource, property, literal);
        statements.add(statement);

        if (!(resource instanceof RDF_Statement)) {
            statement.addProperty(subject, resource);
            statement.addProperty(predicate, property);
            statement.addProperty(object, literal);
        }

        return this;
    }

    public RDFS_Class createResource(String uri) {
        return new RDFS_Class(new UriNode(uri), this);
    }

    public RDF_Statement createStatement(RDF_Statement statement) {
        return new RDF_Statement(statement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(statements, model.statements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statements);
    }

    public RDF_Property getDomain() {
        return domain;
    }

    public RDF_Property getRange() {
        return range;
    }

    public RDF_Property getSubject() {
        return subject;
    }

    public RDF_Property getPredicate() {
        return predicate;
    }

    public RDF_Property getObject() {
        return object;
    }

}
