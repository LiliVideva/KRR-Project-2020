package basic;

import datatype.RDFS_Datatype;
import model.Model;
import node.LiteralNode;
import node.Node;

import java.time.LocalDate;

public class RDFS_Literal extends RDFS_Class {
    public RDFS_Literal() {
        this(new LiteralNode(null), null);
    }

    public RDFS_Literal(Node node, Model model) {
        super(node, model);
    }

    private Object getValue() {
        return asNode().getLiteralValue();
    }

    public RDFS_Datatype getDatatype() {
        return asNode().getLiteralDatatype();
    }

    public String getDatatypeUri() {
        return asNode().getLiteralDatatypeUri();
    }

    public boolean getBoolean() {
        return Boolean.parseBoolean(String.valueOf(getValue()));
    }

    public int getInt() {
        return Integer.parseInt(String.valueOf(getValue()));
    }

    public double getDouble() {
        return Double.parseDouble(String.valueOf(getValue()));
    }

    public String getString() {
        return String.valueOf(getValue());
    }

    public LocalDate getDate() {
        return LocalDate.parse(String.valueOf(getValue()));
    }

    @Override
    public RDFS_Class inModel(Model model) {
        return (getModel().equals(model)) ? this : (RDFS_Literal) model.getRDFResource(asNode());
    }

    @Override
    public RDFS_Class asResource() {
        return null;
    }

    @Override
    public RDFS_Literal asLiteral() {
        return this;
    }

    @Override
    public String toString() {
        return "\"rdfs:literal\": {\n" + asNode().toString() + "\n}";
    }
}
