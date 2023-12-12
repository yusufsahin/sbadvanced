package tr.com.provera.pameraapi.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "Privilege Data Transfer Object")
public class PrivilegeDto {

    @Schema(description = "Privilege ID")
    private Long id;

    @NotNull
    @Schema(description = "Name of the Privilege",required = true)
    private String name;

    @Schema(description = "Description of Privilege")
    private String description;

}
