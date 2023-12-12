package tr.com.provera.pameraapi.service;

import tr.com.provera.pameraapi.dto.UserDto;
import tr.com.provera.pameraapi.dto.UserRegisterDto;
import tr.com.provera.pameraapi.dto.UserResetPasswordDto;
import tr.com.provera.pameraapi.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDto findByUsername(String username);

    UserDto save(UserDto userDto);

    UserDto getById(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);

    List<UserDto> getUsers();

    Boolean delete(Long id);

    UserDto update(Long id, UserDto userDto);

    Boolean register(UserRegisterDto userRegisterDto);

    //Boolean forgotPassword(UserForgotPasswordDto userForgotPasswordDto);

    Boolean resetPassword(UserResetPasswordDto userResetPasswordDto);


    UserDto addRoleByRoleId(Long id, Long roleId);

    UserDto addRoleByRoleName(Long id, String roleName);

}
