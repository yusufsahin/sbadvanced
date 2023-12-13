package tr.com.provera.pameraapi.service;

import tr.com.provera.pameraapi.dto.WorkitemDto;
import tr.com.provera.pameraapi.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WorkitemService {
    WorkitemDto create(WorkitemDto workitemDto);

    WorkitemDto getById(Long id);

    TPage<WorkitemDto> getAllPageable(Pageable pageable);

    List<WorkitemDto> getWorkitems();

    void delete(Long id);

    WorkitemDto update(Long id, WorkitemDto workitemDto);

    List<WorkitemDto> findByProjectId(Long id);

}
