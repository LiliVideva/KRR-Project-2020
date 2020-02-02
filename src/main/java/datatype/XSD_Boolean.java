package datatype;

public class XSD_Boolean extends RDFS_Datatype {
    public XSD_Boolean(String typeName, Class<?> javaClass) {
        super(typeName, javaClass);
    }

    @Override
    public boolean isValidValue(Object value) {
        return (value instanceof Boolean);
    }
}
