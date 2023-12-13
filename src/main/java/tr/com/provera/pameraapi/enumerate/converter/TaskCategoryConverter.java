package tr.com.provera.pameraapi.enumerate.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tr.com.provera.pameraapi.enumerate.ProjectStatus;
import tr.com.provera.pameraapi.enumerate.TaskCategory;
@Converter(autoApply = true)
public class TaskCategoryConverter implements AttributeConverter<TaskCategory, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TaskCategory taskCategory) {
        if (taskCategory == null) return null;
        return taskCategory.getId();
    }

    @Override
    public TaskCategory convertToEntityAttribute(Integer databaseValue) {
        if (databaseValue == null) return null;
        return TaskCategory.valueOf(databaseValue);}
}
