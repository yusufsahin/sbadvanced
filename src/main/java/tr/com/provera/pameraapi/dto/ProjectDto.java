package tr.com.provera.pameraapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.provera.pameraapi.enumerate.ProjectStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Project Data Transfer Object")
public class ProjectDto {
    @Schema(description = "Project ID")
    private Long id;

    @NotNull
    @Schema(required = true,description = "Name Of Project")
    private String name;

    @Schema(description = "Description Of Project")
    private String description;

    @Schema(description = "Scope Of Project")
    private String scope;

    @Schema(description = "Memo Of Project")
    private String memo;

    @Schema(description = "Project Manager Of Project")
    private String projectManager;

    @Schema(description = "Project Assistant Of Project")
    private String projectAssistant;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(description = "Start Date Of Project")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(description = "Finish Date Of Project")
    private Date finishDate;


    @Schema(description = "Status Of Project")
    private ProjectStatus status;
}
