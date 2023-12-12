package tr.com.provera.pameraapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.provera.pameraapi.dao.PrivilegeRepository;
import tr.com.provera.pameraapi.dao.RoleRepository;
import tr.com.provera.pameraapi.dao.model.Privilege;
import tr.com.provera.pameraapi.dao.model.Role;
import tr.com.provera.pameraapi.dto.RoleDto;
import tr.com.provera.pameraapi.service.PrivilegeService;
import tr.com.provera.pameraapi.service.RoleService;
import tr.com.provera.pameraapi.util.TPage;

import java.util.List;

import org.springframework.data.domain.Page;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PrivilegeRepository privilegeRepository;



    @Override
    public RoleDto save(RoleDto roleDto)  {
        // Optional<Role> checkRole= roleRepository.findById(roleDto.getId());
        //  if(checkRole.isPresent())
        //       throw new  IllegalArgumentException("Role Already Exist");
        Role role = modelMapper.map(roleDto, Role.class);
        role = roleRepository.save(role);
        roleDto.setId(role.getId());
        return roleDto;

    }

    @Override
    public RoleDto getById(Long id) throws Exception {

        Optional<Role> optionalRole = roleRepository.findById(id);

        if(!optionalRole.isPresent())
            throw new Exception("Role not found");

        return modelMapper.map(optionalRole.get(),RoleDto.class);
    }


    @Override
    public List<RoleDto> getRoles() {
        List<Role> data= roleRepository.findAll();
        return Arrays.asList(modelMapper.map(data,RoleDto[].class));
    }
    @Override
    public TPage<RoleDto> getAllPageable(Pageable pageable) {
        Page<Role> data = roleRepository.findAll(pageable);
        List<RoleDto> roleDtos = data.getContent().stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());

        TPage<RoleDto> response = new TPage<>();
        response.setPageData(data, roleDtos); // Ensure that the method name matches the one in TPage class
        return response;
    }


    @Override
    public Boolean delete(Long id) {
        roleRepository.deleteById(id);
        return true;
    }

    @Override
    public RoleDto update(Long id, RoleDto roleDto) {
        Optional<Role> optionalRole= roleRepository.findById(id);
        if(optionalRole.isEmpty())
            throw  new IllegalArgumentException("Role not found");
        optionalRole.get().setName(roleDto.getName());
        optionalRole.get().setDescription(roleDto.getDescription());
        roleRepository.save(optionalRole.get());
        return modelMapper.map(optionalRole, RoleDto.class);
    }

    public RoleDto getByName(String name){
        Role role = roleRepository.findByName(name);
        if(role == null)
            throw new IllegalArgumentException("Role not found");
        return modelMapper.map(role,RoleDto.class);
    }




    @Override
    public RoleDto addPrivilegeByPrivilegeId(Long id, Long privilegeId){
        try{
            Optional<Role> role = roleRepository.findById(id);
            if(role.isPresent()){
                List<Privilege> oldPrivileges = new ArrayList<>();
                Collection<Privilege> privileges = role.get().getPrivileges();
                boolean hasPrivilege = false;
                for(Privilege p : privileges){
                    boolean isCopied = false;
                    if(p.getId() == privilegeId){
                        hasPrivilege = true;
                    }
                    for(Privilege p2 : oldPrivileges){
                        if(p.getName() == p2.getName()){
                            isCopied = true;
                        }
                    }
                    if(!isCopied)
                        oldPrivileges.add(p);
                }
                if(!hasPrivilege){
                    Privilege privilege =privilegeRepository.getReferenceById(privilegeId);
                    oldPrivileges.add(privilege);
                    role.get().setPrivileges(oldPrivileges);
                    roleRepository.save(role.get());
                    return modelMapper.map(role.get(),RoleDto.class);
                }

                throw new IllegalArgumentException("User already has this role");
            }
            throw new IllegalArgumentException("User Not Found");
        }
        catch (Exception e){
            throw new IllegalArgumentException("Role or User Not Found");
        }
    }

    @Override
    public RoleDto addPrivilegeByPrivilegeName(Long id, String privilegeName){
        try{
            Optional<Role> role = roleRepository.findById(id);
            if(role.isPresent()){
                List<Privilege> oldPrivileges = new ArrayList<>();
                Collection<Privilege> privileges = role.get().getPrivileges();
                boolean hasPrivilege = false;
                for(Privilege p : privileges){
                    boolean isCopied = false;
                    if(p.getName() == privilegeName){
                        hasPrivilege = true;
                    }
                    for(Privilege p2 : oldPrivileges){
                        if(p.getName() == p2.getName()){
                            isCopied = true;
                        }
                    }
                    if(!isCopied)
                        oldPrivileges.add(p);
                }
                if(!hasPrivilege){
                    Privilege privilege = privilegeRepository.findByName(privilegeName);
                    oldPrivileges.add(privilege);
                    role.get().setPrivileges(oldPrivileges);
                    roleRepository.save(role.get());
                    return modelMapper.map(role.get(),RoleDto.class);
                }

                throw new IllegalArgumentException("User already has this role");
            }
            throw new IllegalArgumentException("User Not Found");
        }
        catch (Exception e){
            throw new IllegalArgumentException("Role or User Not Found");
        }
    }



}
