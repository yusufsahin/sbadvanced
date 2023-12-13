package tr.com.provera.pameraapi.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import tr.com.provera.pameraapi.enumerate.WorkitemCategory;
import tr.com.provera.pameraapi.enumerate.WorkitemState;
import tr.com.provera.pameraapi.enumerate.WorkitemType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Workitem Data Transfer Object")
public class WorkitemDto {

    @Schema(description = "Id Of Workitem")
    private Long id;

    @NotNull
    @Schema(required = true,description = "Name Of Workitem")
    private String name;


    @Schema(description = "Description Of Workitem")
    private String description;

    @Schema(description = "Point Of Workitem")
    private Integer point;

    @Schema(description = "DueDate Of Workitem")
    private Date dueDate;

    @Schema(description = "ExpectedDate Of Workitem")
    private Date expectedDate;

    @Schema(description = "ActualDate Of Workitem")
    private Date actualDate;

    @Schema(description = "ResponsibleUser Of Workitem")
    private String responsibleUser;

    @Schema(description = "Type Of Workitem")
    private WorkitemType type;

    @Schema(description = "Category Of Workitem")
    private WorkitemCategory category;

    @Schema(description = "State Of Workitem")
    private WorkitemState state;

    private Set<TaskDto> tasks;
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("projectId")
    private Long projectId;

/*
    @Schema(description = "ProjectDto Of Workitem")
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("projectid")
    private ProjectDto project;
*/

}
