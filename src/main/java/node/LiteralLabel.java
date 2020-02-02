package node;

import datatype.RDFS_Datatype;
import datatype.TypeMapper;

import java.util.Objects;

public class LiteralLabel {
    private Object value;
    private RDFS_Datatype datatype;

    public LiteralLabel(Object value, RDFS_Datatype datatype) {
        this.value = value;
        this.datatype = datatype;
    }

    public LiteralLabel(Object value) {
        RDFS_Datatype datatype = new TypeMapper().getTypeByValue(value);
        this.value = value;
        this.datatype = datatype;
    }

    public Object getValue() {
        return value;
    }

    public RDFS_Datatype getDatatype() {
        return datatype;
    }

    public String getDatatypeUri() {
        if (datatype == null) {
            return null;
        }
        return datatype.getURI();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiteralLabel that = (LiteralLabel) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(datatype, that.datatype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, datatype);
    }

    @Override
    public String toString() {
        return value.toString();
//        return "\"value\": \"" + value + "\",\n"
//                + datatype.toString();
    }
}
