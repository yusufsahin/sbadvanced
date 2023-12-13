package tr.com.provera.pameraapi.enumerate.converter;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tr.com.provera.pameraapi.enumerate.TaskType;


@Converter(autoApply = true)
public class TaskTypeConverter implements AttributeConverter<TaskType,Integer> {

    public Integer convertToDatabaseColumn(TaskType taskType) {
        if (taskType == null) return null;
        return taskType.getId();
    }

    @Override
    public TaskType convertToEntityAttribute(Integer databaseValue) {
        if (databaseValue == null) return null;
        return TaskType.valueOf(databaseValue);}
}

