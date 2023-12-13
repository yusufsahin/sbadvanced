package tr.com.provera.pameraapi.enumerate.converter;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tr.com.provera.pameraapi.enumerate.WorkitemCategory;

@Converter(autoApply = true)
public class WorkitemCategoryConverter implements AttributeConverter<WorkitemCategory,Integer> {
    @Override
    public Integer convertToDatabaseColumn(WorkitemCategory workitemCategory) {
        if(workitemCategory==null) return null;
        return workitemCategory.getId();
    }
    @Override
    public WorkitemCategory convertToEntityAttribute(Integer databaseValue) {
        if(databaseValue==null) return null;
        return WorkitemCategory.valueOf(databaseValue);
    }
}
