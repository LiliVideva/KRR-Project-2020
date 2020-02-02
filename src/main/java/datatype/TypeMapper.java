package datatype;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public final class TypeMapper {
    private static final RDFS_Datatype XSD_double = new XSD_Double("xsd:double", Double.class);
    private static final RDFS_Datatype XSD_integer = new XSD_Integer("xsd:integer", Integer.class);
    private static final RDFS_Datatype XSD_boolean = new XSD_Boolean("xsd:boolean", Boolean.class);
    private static final RDFS_Datatype XSD_string = new XSD_String("xsd:string", String.class);
    private static final RDFS_Datatype XSD_date = new XSD_Date("xsd:date", LocalDate.class);

    private Map<Class<?>, RDFS_Datatype> classToDatatypeMap;
    private Map<String, RDFS_Datatype> uriToDatatypeMap;

    public TypeMapper() {
        classToDatatypeMap = new HashMap<>();
        uriToDatatypeMap = new HashMap<>();

        addDatatype(XSD_boolean);
        addDatatype(XSD_double);
        addDatatype(XSD_integer);
        addDatatype(XSD_date);
        addDatatype(XSD_string);
    }

    public void addDatatype(RDFS_Datatype datatype) {
        uriToDatatypeMap.put(datatype.getURI(), datatype);
        classToDatatypeMap.put(datatype.getDatatypeClass(), datatype);
    }

    public RDFS_Datatype getTypeByValue(Object value) {
        return classToDatatypeMap.get(value.getClass());
    }

    public RDFS_Datatype getTypeByUri(String uri) {
        return uriToDatatypeMap.get(uri);
    }
}
