package tr.com.provera.pameraapi.service;

import tr.com.provera.pameraapi.dto.TaskDto;
import tr.com.provera.pameraapi.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
   // TaskDto save(TaskDto taskDto);

    TaskDto create(TaskDto taskDto);

    TaskDto getById(Long workitemId,Long id);
    TaskDto getById(Long id);
    TPage<TaskDto> getAllPageable(Pageable pageable);

    List<TaskDto> getTasks();

    void delete(Long id);

    TaskDto update(Long id, TaskDto taskRequest);

    List<TaskDto> findByWorkitemId(Long workitemId);

    List<TaskDto> findByAssignTo(String assignTo);
}
