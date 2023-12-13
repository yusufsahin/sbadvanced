package tr.com.provera.pameraapi.enumerate.converter;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tr.com.provera.pameraapi.enumerate.WorkitemType;

@Converter(autoApply = true)
public class WorkitemTypeConverter implements AttributeConverter<WorkitemType,Integer> {
    @Override
    public Integer convertToDatabaseColumn(WorkitemType workitemType) {
        if(workitemType==null) return null;
        return workitemType.getId();
    }
    @Override
    public WorkitemType convertToEntityAttribute(Integer databaseValue) {
        if(databaseValue==null) return null;
        return WorkitemType.valueOf(databaseValue);
    }
}
