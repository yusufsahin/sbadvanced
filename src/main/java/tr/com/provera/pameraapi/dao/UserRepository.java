package tr.com.provera.pameraapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tr.com.provera.pameraapi.dao.model.User;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);
    //User findByUsernameOrEmail(String username, String email);
    User findByForgotPasswordGuid(UUID forgotPasswordGuid);

}
