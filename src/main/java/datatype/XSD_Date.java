package datatype;

import java.time.LocalDate;

public class XSD_Date extends RDFS_Datatype {
    public XSD_Date(String typeName, Class<?> javaClass) {
        super(typeName, javaClass);
    }

    @Override
    public boolean isValidValue(Object value) {
        return (value instanceof LocalDate);
    }
}
