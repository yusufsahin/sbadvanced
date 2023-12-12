package tr.com.provera.pameraapi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.provera.pameraapi.dao.ProjectRepository;
import tr.com.provera.pameraapi.dao.model.Project;
import tr.com.provera.pameraapi.dto.ProjectDto;
import tr.com.provera.pameraapi.service.ProjectService;
import tr.com.provera.pameraapi.util.TPage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ProjectDto create(ProjectDto projectDto) {
        logger.info("Creating new project");
        Project project = modelMapper.map(projectDto, Project.class);
        project = projectRepository.save(project);
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto getById(Long id) {
        logger.info("Retrieving project with id {}", id);
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
        return modelMapper.map(project, ProjectDto.class);
    }
    @Override
    @Transactional(readOnly = true)
    public TPage<ProjectDto> getAllPageable(Pageable pageable) {
        logger.info("Retrieving all projects in a paginated manner");
        Page<Project> projects = projectRepository.findAll(pageable);
        List<ProjectDto> projectDtos = projects.getContent()
                .stream()
                .map(project -> modelMapper.map(project, ProjectDto.class))
                .collect(Collectors.toList());

        TPage<ProjectDto> projectDtoTPage = new TPage<>();
        projectDtoTPage.setPageData(projects, projectDtos);

        return projectDtoTPage;
    }

    @Override
    public List<ProjectDto> getAllFilter(Specification<ProjectDto> spec) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> getProjects() {
        logger.info("Retrieving all projects");
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(project -> modelMapper.map(project, ProjectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        logger.info("Deleting project with id {}", id);
        projectRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProjectDto update(Long id, ProjectDto projectDto) {
        logger.info("Updating project with id {}", id);
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
        modelMapper.map(projectDto, existingProject);
        existingProject = projectRepository.save(existingProject);
        return modelMapper.map(existingProject, ProjectDto.class);
    }

    // The method getAllFilter is not implemented as it requires a specific implementation based on your project's needs.
    // If needed, you should provide a suitable implementation for this method.
}
