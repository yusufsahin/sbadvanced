package tr.com.provera.pameraapi.enumerate.converter;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tr.com.provera.pameraapi.enumerate.TaskStatus;

@Converter(autoApply = true)
public class TaskStatusConverter implements AttributeConverter<TaskStatus, Integer> {
    public Integer convertToDatabaseColumn(TaskStatus taskStatus) {
        if (taskStatus == null) return null;
        return taskStatus.getId();
    }

    @Override
    public TaskStatus convertToEntityAttribute(Integer databaseValue) {
        if (databaseValue == null) return null;
        return TaskStatus.valueOf(databaseValue);}
}

