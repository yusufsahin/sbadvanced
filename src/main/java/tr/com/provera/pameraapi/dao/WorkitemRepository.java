package tr.com.provera.pameraapi.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tr.com.provera.pameraapi.dao.model.Workitem;

import java.util.List;


@Repository
public interface WorkitemRepository extends JpaRepository<Workitem,Long>, JpaSpecificationExecutor<Workitem> {
    List<Workitem> findByProjectId(Long id);
}
