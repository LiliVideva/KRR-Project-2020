package datatype;

import basic.RDFS_Class;

import java.util.Objects;

public class RDFS_Datatype extends RDFS_Class {
    private static final String XSD = "http://www.w3.org/2001/XMLSchema";
    private String uri;
    private Class<?> datatypeClass;

    public RDFS_Datatype(String uri, Class<?> datatypeClass) {
        super();
        this.uri = uri;
        this.datatypeClass = datatypeClass;
    }

    public String getURI() {
        return uri;
    }

    public Class<?> getDatatypeClass() {
        return datatypeClass;
    }

    public boolean isValidValue(Object value) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RDFS_Datatype that = (RDFS_Datatype) o;
        return Objects.equals(uri, that.uri) &&
                Objects.equals(datatypeClass, that.datatypeClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, datatypeClass);
    }

    @Override
    public String toString() {
        return "\"rdfs:datatype\": \"" + uri + "\"\n";
    }
}
