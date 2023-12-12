package tr.com.provera.pameraapi.service;

import org.springframework.data.domain.Pageable;
import tr.com.provera.pameraapi.dao.model.Role;
import tr.com.provera.pameraapi.dto.RoleDto;
import tr.com.provera.pameraapi.util.TPage;

import java.util.List;

public interface RoleService {
    RoleDto save(RoleDto roleDto);

    RoleDto getById(Long id) throws Exception;

    List<RoleDto> getRoles();

    TPage<RoleDto> getAllPageable(Pageable pageable);

    Boolean delete(Long id);

    RoleDto update(Long id, RoleDto roleDto);

    RoleDto getByName(String name);


    RoleDto addPrivilegeByPrivilegeId(Long id, Long privilegeId);

    RoleDto addPrivilegeByPrivilegeName(Long id, String privilegeName);
    //Boolean register(UserRegisterDto userRegisterDto);

    //Boolean forgotPassword(UserForgotPasswordDto userForgotPasswordDto);

    //Boolean resetPassword(UserResetPasswordDto userResetPasswordDto);

}
