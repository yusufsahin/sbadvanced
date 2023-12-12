package tr.com.provera.pameraapi.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import tr.com.provera.pameraapi.dto.ProjectDto;
import tr.com.provera.pameraapi.util.TPage;

import java.util.List;

public interface ProjectService {
    ProjectDto create(ProjectDto projectDto);

    ProjectDto getById(Long id);

    TPage<ProjectDto> getAllPageable(Pageable pageable);

    List<ProjectDto> getAllFilter(Specification<ProjectDto> spec);

    List<ProjectDto> getProjects();

    void delete(Long id);

    ProjectDto update(Long id, ProjectDto projectDto);
}
