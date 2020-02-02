package datatype;

public class XSD_Double extends RDFS_Datatype {
    public XSD_Double(String typeName, Class<?> javaClass) {
        super(typeName, javaClass);
    }

    @Override
    public boolean isValidValue(Object value) {
        return (value instanceof Double);
    }
}
