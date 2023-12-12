package tr.com.provera.pameraapi.dao.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tr.com.provera.pameraapi.dao.common.BaseModel;
import tr.com.provera.pameraapi.enumerate.ProjectStatus;
import tr.com.provera.pameraapi.enumerate.converter.ProjectStatusConverter;


import java.util.Date;


@SuppressWarnings("serial")
@Entity
@Table(name = "Projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "isdeleted='false'")
@SQLDelete(sql = "UPDATE projects SET isdeleted = true WHERE id = ? and version = ?")
@EntityListeners(AuditingEntityListener.class)
public class Project extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Memo")
    private String memo;

    @Column(name = "Scope")
    private String scope;

    @Column(name = "ProjectManager")
    private String projectManager;

    @Column(name = "ProjectAssistant")
    private String projectAssistant;

    @Temporal(TemporalType.DATE)
    @Column(name = "StartDate")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "FinishDate")
    private Date finishDate;

    @Convert(converter = ProjectStatusConverter.class)
    @Column(name = "Status")
    private ProjectStatus status;

}
