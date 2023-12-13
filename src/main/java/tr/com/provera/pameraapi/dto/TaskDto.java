package tr.com.provera.pameraapi.dto;


import tr.com.provera.pameraapi.enumerate.TaskCategory;
import tr.com.provera.pameraapi.enumerate.TaskStatus;
import tr.com.provera.pameraapi.enumerate.TaskType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Task Data Transfer Object")
public class TaskDto {


    @Schema(description = "Id Of Task")
    private Long id;

    @NotNull
    @Schema(required = true, description = "Name Of Task")
    private String name;


    @Schema(description = "Description Of Task")
    private String description;


    @Schema(description = "HoursExpected Of Task")
    private Integer hoursExpected;

    @Schema(description = "HoursActual Of Task")
    private Integer hoursActual;


    @Schema(description = "DueDate Of Task")
    private Date dueDate;

    @Schema(description = "ExpectedDate Of Task")
    private Date expectedDate;

    @Schema(description = "ActualDate Of Task")
    private Date actualDate;

    @Schema(description = "AssingTo Of Task")
    private String assignTo;

    @Schema(description = "Type Of Task")
    private TaskType type;

    @Schema(description = "Category Of Task")
    private TaskCategory category;

    @Schema(description = "Status Of Task")
    private TaskStatus status;

    private Long workitemId;
/*
    @Schema(description = "Workitem Of Task")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("workitemid")
    private WorkitemDto workitem;

 */
}