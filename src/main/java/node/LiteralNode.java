package node;

import datatype.RDFS_Datatype;

import java.util.Objects;

public class LiteralNode extends Node {
    public LiteralNode(LiteralLabel label) {
        super(label);
    }

    @Override
    public boolean isLiteral() {
        return true;
    }

    @Override
    public LiteralLabel getLiteral() {
        return (LiteralLabel) getLabel();
    }

    @Override
    public final Object getLiteralValue() {
        return getLiteral().getValue();
    }

    @Override
    public final String getLiteralDatatypeUri() {
        return getLiteral().getDatatypeUri();
    }

    @Override
    public final RDFS_Datatype getLiteralDatatype() {
        return getLiteral().getDatatype();
    }

    @Override
    public String toString() {
        return getLiteral().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LiteralNode node = (LiteralNode) o;
        return Objects.equals(getLabel(), node.getLabel());
    }
}
