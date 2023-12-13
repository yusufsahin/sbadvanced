package tr.com.provera.pameraapi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.provera.pameraapi.dao.ProjectRepository;
import tr.com.provera.pameraapi.dao.WorkitemRepository;
import tr.com.provera.pameraapi.dao.model.Project;
import tr.com.provera.pameraapi.dao.model.Workitem;
import tr.com.provera.pameraapi.dto.WorkitemDto;
import tr.com.provera.pameraapi.service.WorkitemService;
import tr.com.provera.pameraapi.util.TPage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkitemServiceImpl implements WorkitemService {
    @Autowired
    private WorkitemRepository workitemRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public WorkitemDto create(WorkitemDto workitemDto) {
        Project project = projectRepository.findById(workitemDto.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found " + workitemDto.getProjectId()));

        Workitem workitem = mapToEntity(workitemDto);
        workitem.setProject(project);
        Workitem newWorkitem = workitemRepository.save(workitem);

        return mapToDTO(newWorkitem);
    }

    @Override
    public WorkitemDto getById(Long id) {
        Workitem workitem = workitemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Workitem not found " + id));
        return mapToDTO(workitem);
    }

    @Override
    public TPage<WorkitemDto> getAllPageable(Pageable pageable) {
        Page<Workitem> data = workitemRepository.findAll(pageable);
        List<WorkitemDto> workitemDtos = data.getContent().stream()
                .map(workitem -> modelMapper.map(workitem, WorkitemDto.class))
                .collect(Collectors.toList());

        TPage<WorkitemDto> response = new TPage<>();
        response.setPageData(data, workitemDtos);
        return response;
    }

    @Override
    public List<WorkitemDto> getWorkitems() {
        List<WorkitemDto> workitemDtos = workitemRepository.findAll().stream()
                .map(workitem -> modelMapper.map(workitem, WorkitemDto.class))
                .collect(Collectors.toList());

        return workitemDtos;
    }

    @Override
    public void delete(Long id) {
        Workitem workitem = workitemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workitem not found " + id));

        workitemRepository.delete(workitem);
    }

    @Override
    public WorkitemDto update(Long id, WorkitemDto workitemDto) {
        Workitem workitem = workitemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workitem not found " + id));

        modelMapper.map(workitemDto, workitem);
        workitemRepository.save(workitem);

        return mapToDTO(workitem);
    }

    @Override
    public List<WorkitemDto> findByProjectId(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found " + id));

        List<WorkitemDto> workitemDtos = workitemRepository.findByProjectId(id).stream()
                .map(workitem -> modelMapper.map(workitem, WorkitemDto.class))
                .collect(Collectors.toList());

        return workitemDtos;
    }

    private Workitem mapToEntity(WorkitemDto workitemDto) {
        return modelMapper.map(workitemDto, Workitem.class);
    }

    private WorkitemDto mapToDTO(Workitem workitem) {
        return modelMapper.map(workitem, WorkitemDto.class);
    }
}
