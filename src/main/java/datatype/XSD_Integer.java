package datatype;

public class XSD_Integer extends RDFS_Datatype {
    public XSD_Integer(String typeName, Class<?> javaClass) {
        super(typeName, javaClass);
    }

    @Override
    public boolean isValidValue(Object value) {
        return (value instanceof Integer);
    }
}
