package tr.com.provera.pameraapi.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.provera.pameraapi.dao.model.RefreshToken;
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    public RefreshToken findByToken(String token);
}
