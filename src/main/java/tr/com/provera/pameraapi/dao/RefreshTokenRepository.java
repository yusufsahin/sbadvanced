package tr.com.provera.pameraapi.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.provera.pameraapi.dao.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    public RefreshToken findByToken(String token);
}
