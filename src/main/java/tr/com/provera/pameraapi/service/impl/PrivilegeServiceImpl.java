package tr.com.provera.pameraapi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.provera.pameraapi.dao.PrivilegeRepository;
import tr.com.provera.pameraapi.dao.model.Privilege;
import tr.com.provera.pameraapi.dto.PrivilegeDto;
import tr.com.provera.pameraapi.service.PrivilegeService;
import tr.com.provera.pameraapi.util.TPage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PrivilegeDto save(PrivilegeDto privilegeDto) {
        Privilege privilege = modelMapper.map(privilegeDto, Privilege.class);
        privilege = privilegeRepository.save(privilege);
        return modelMapper.map(privilege, PrivilegeDto.class);
    }

    @Override
    public PrivilegeDto getById(Long id) {
        Privilege privilege = privilegeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Privilege not found"));
        return modelMapper.map(privilege, PrivilegeDto.class);
    }

    @Override
    public List<PrivilegeDto> getPrivileges() {
        List<Privilege> privileges = privilegeRepository.findAll();
        return privileges.stream()
                .map(privilege -> modelMapper.map(privilege, PrivilegeDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public TPage<PrivilegeDto> getAllPageable(Pageable pageable) {
        Page<Privilege> privileges = privilegeRepository.findAll(pageable);
        List<PrivilegeDto> privilegeDtos = privileges.getContent()
                .stream()
                .map(privilege -> modelMapper.map(privilege, PrivilegeDto.class))
                .collect(Collectors.toList());

        TPage<PrivilegeDto> privilegeDtoTPage = new TPage<>();
        privilegeDtoTPage.setPageData(privileges, privilegeDtos);

        return privilegeDtoTPage;
    }

    @Override
    public Boolean delete(Long id) {
        privilegeRepository.deleteById(id);
        return !privilegeRepository.existsById(id);
    }

    @Override
    public PrivilegeDto update(Long id, PrivilegeDto privilegeDto) {
        Privilege existingPrivilege = privilegeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Privilege not found"));
        // Update properties
        // existingPrivilege.set...();
        privilegeRepository.save(existingPrivilege);
        return modelMapper.map(existingPrivilege, PrivilegeDto.class);
    }


}
