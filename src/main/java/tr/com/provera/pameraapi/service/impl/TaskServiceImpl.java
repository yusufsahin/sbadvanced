package tr.com.provera.pameraapi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.provera.pameraapi.dao.TaskRepository;
import tr.com.provera.pameraapi.dao.WorkitemRepository;
import tr.com.provera.pameraapi.dao.model.Task;
import tr.com.provera.pameraapi.dao.model.Workitem;
import tr.com.provera.pameraapi.dto.TaskDto;
import tr.com.provera.pameraapi.service.TaskService;
import tr.com.provera.pameraapi.util.TPage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ModelMapper modelMapper;  // Inject ModelMapper

    @Autowired
    private WorkitemRepository workitemRepository;

    @Override
    public TaskDto create(TaskDto taskDto) {
        Task task = mapToEntity(taskDto);
        task.setId(0L);

        Workitem workitem = workitemRepository.findById(taskDto.getWorkitemId()).orElseThrow(
                () -> new EntityNotFoundException("Workitem not found" + taskDto.getWorkitemId()));

        task.setWorkitem(workitem);
        Task newTask = taskRepository.save(task);

        return mapToDTO(newTask);
    }

    @Override
    public TaskDto getById(Long workitemId, Long id) {
        Workitem post = workitemRepository.findById(workitemId).orElseThrow(
                () -> new EntityNotFoundException("Workitem not found" + workitemId));

        Task task = taskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Task not found" + id));

        if (!task.getWorkitem().getId().equals(post.getId())) {
            throw new IllegalArgumentException("Task does not belong to workitem id" + workitemId);
        }

        return mapToDTO(task);
    }

    @Override
    public TaskDto getById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Task not found" + id));

        return mapToDTO(task);
    }

    @Override
    public TPage<TaskDto> getAllPageable(Pageable pageable) {
        Page<Task> data = taskRepository.findAll(pageable);
        TPage<TaskDto> response = new TPage<>();
        response.setPageData(data, Arrays.asList(modelMapper.map(data.getContent(), TaskDto[].class)));
        return response;
    }

    @Override
    public List<TaskDto> getTasks() {
        List<Task> data = taskRepository.findAll();
        return Arrays.asList(modelMapper.map(data, TaskDto[].class));
    }

    @Override
    public void delete(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found" + id));
        taskRepository.delete(task);
    }

    @Override
    public TaskDto update(Long id, TaskDto taskRequest) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found" + id));

        // Map fields using ModelMapper
        modelMapper.map(taskRequest, task);

        Task updatedTask = taskRepository.save(task);
        return mapToDTO(updatedTask);
    }

    @Override
    public List<TaskDto> findByWorkitemId(Long workitemId) {
        List<Task> tasks = taskRepository.findByWorkitemId(workitemId);
        return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findByAssignTo(String assignTo) {
        List<Task> tasks = taskRepository.findByAssignTo(assignTo);
        return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private TaskDto mapToDTO(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }

    private Task mapToEntity(TaskDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }
}
