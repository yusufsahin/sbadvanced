package tr.com.provera.pameraapi.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.provera.pameraapi.dao.model.Privilege;
import tr.com.provera.pameraapi.dto.PrivilegeDto;
import tr.com.provera.pameraapi.util.TPage;

import java.util.List;

public interface PrivilegeService {

    PrivilegeDto save(PrivilegeDto privilegeDto);

    PrivilegeDto getById(Long id);

    List<PrivilegeDto> getPrivileges();

    TPage<PrivilegeDto> getAllPageable(Pageable pageable);

    Boolean delete(Long id);

    PrivilegeDto update(Long id, PrivilegeDto privilegeDto);


}
