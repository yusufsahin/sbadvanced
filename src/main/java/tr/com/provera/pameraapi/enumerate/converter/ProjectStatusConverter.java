package tr.com.provera.pameraapi.enumerate.converter;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tr.com.provera.pameraapi.enumerate.ProjectStatus;

@Converter(autoApply = true)
public class ProjectStatusConverter implements AttributeConverter<ProjectStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProjectStatus projectStatus) {
        if (projectStatus == null) return null;
        return projectStatus.getId();
    }

    @Override
    public ProjectStatus convertToEntityAttribute(Integer databaseValue) {
        if (databaseValue == null) return null;
        return ProjectStatus.valueOf(databaseValue);}
}
