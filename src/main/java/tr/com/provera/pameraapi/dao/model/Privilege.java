package tr.com.provera.pameraapi.dao.model;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import tr.com.provera.pameraapi.dao.common.BaseModel;

import java.util.Collection;

@SuppressWarnings("serial")
@Entity
@Builder

@Table(name = "Privileges",uniqueConstraints = {
        @UniqueConstraint(name = "UniquePrivilegeName", columnNames = {"name"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Where(clause = "isdeleted='false'")
@SQLDelete(sql = "UPDATE privileges SET isdeleted = true WHERE id = ? and version = ?")

@EntityListeners(AuditingEntityListener.class)

public class Privilege  extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name",unique = true)
    private String name;

    @Column(name = "Description")
    private String description;

    @ManyToMany(mappedBy="privileges")
    private Collection<Role> roles;


}
