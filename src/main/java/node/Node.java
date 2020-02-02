package node;

import datatype.RDFS_Datatype;

import java.util.Objects;

public abstract class Node {
    static final String RDFprefix = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private final Object label;

    Node(Object label) {
        this.label = label;
    }

    public boolean isLiteral() {
        return false;
    }

    public LiteralLabel getLiteral() {
        throw new UnsupportedOperationException(this + " is not a literal node");
    }

    public Object getLiteralValue() {
        throw new UnsupportedOperationException(this + " is not a literal node");
    }

    public String getLiteralDatatypeUri() {
        throw new UnsupportedOperationException(this + " is not a literal node");
    }

    public RDFS_Datatype getLiteralDatatype() {
        throw new UnsupportedOperationException(this + " is not a literal node");
    }

    public boolean isURI() {
        return false;
    }

    public String getURI() {
        throw new UnsupportedOperationException(this + " is not a URI node");
    }

    public String getNameSpace() {
        throw new UnsupportedOperationException(this + " is not a URI node");
    }

    public String getLocalName() {
        throw new UnsupportedOperationException(this + " is not a URI node");
    }

    public Object getLabel() {
        return label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public abstract String toString();
}
