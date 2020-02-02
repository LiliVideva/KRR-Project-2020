package datatype;

public class XSD_String extends RDFS_Datatype {
    public XSD_String(String typeName, Class<?> javaClass) {
        super(typeName, javaClass);
    }

    @Override
    public boolean isValidValue(Object value) {
        return (value instanceof String);
    }

}
