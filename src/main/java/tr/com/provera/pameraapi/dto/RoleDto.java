package tr.com.provera.pameraapi.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Role Data Transfer Object")
public class RoleDto {
    @Schema(description = "Role ID")
    private Long id;

    @NotNull
    @Schema(required = true,description = "Name Of Role")
    private String name;

    @Schema(description = "Description Of Role")
    private String description;
}
