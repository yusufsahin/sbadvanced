package tr.com.provera.pameraapi.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tr.com.provera.pameraapi.dao.model.Task;

import java.util.List;

//@JaversSpringDataAuditable
@Repository
public interface TaskRepository extends JpaRepository<Task,Long>, JpaSpecificationExecutor<Task> {

    List<Task> findByWorkitemId(Long workItemId);

    List<Task> findByAssignTo(String assignTo);
}
