package tr.com.provera.pameraapi.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tr.com.provera.pameraapi.dao.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>, JpaSpecificationExecutor<Project> {
}
