package node;

import java.util.Objects;

public class UriNode extends Node {
    public UriNode(String uri) {
        super(uri);
    }

    @Override
    public boolean isURI() {
        return true;
    }

    @Override
    public String getURI() {
        return (String) getLabel();
    }

    @Override
    public String getNameSpace() {
        return getURI().split(":")[0];
    }

    @Override
    public String getLocalName() {
        return getURI().split(":")[1];
    }

    @Override
    public String toString() {
        return getURI();
//        return "\"uri\": \"" + getURI() + "\"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UriNode node = (UriNode) o;
        return Objects.equals(getLabel(), node.getLabel());
    }
}
