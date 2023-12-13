package tr.com.provera.pameraapi.enumerate.converter;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tr.com.provera.pameraapi.enumerate.WorkitemState;

@Converter(autoApply = true)
public class WorkitemStateConverter implements AttributeConverter<WorkitemState,Integer> {
    @Override
    public Integer convertToDatabaseColumn(WorkitemState workitemState) {
        if(workitemState ==null) return null;
        return workitemState.getId();
    }
    @Override
    public WorkitemState convertToEntityAttribute(Integer databaseValue) {
        if(databaseValue==null) return null;
        return WorkitemState.valueOf(databaseValue);
    }
}
