package tr.com.provera.pameraapi.enumerate.converter;

import jakarta.persistence.AttributeConverter;
import tr.com.provera.pameraapi.enumerate.ProjectStatus;
import tr.com.provera.pameraapi.enumerate.TaskCategory;

public class TaskCategoryConverter implements AttributeConverter<TaskCategory, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TaskCategory projectStatus) {
        if (projectStatus == null) return null;
        return projectStatus.getId();
    }

    @Override
    public ProjectStatus convertToEntityAttribute(Integer databaseValue) {
        if (databaseValue == null) return null;
        return ProjectStatus.valueOf(databaseValue);}
}
