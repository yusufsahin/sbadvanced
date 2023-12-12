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

@Table(name = "Roles",uniqueConstraints = {
        @UniqueConstraint(name = "UniqueRoleName", columnNames = {"name"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Where(clause = "isdeleted='false'")
@SQLDelete(sql = "UPDATE roles SET isdeleted = true WHERE id = ? and version = ?")
//@SQLDelete(sql = "UPDATE roles SET isdeleted = true WHERE id = ?")

@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name="Name", nullable = false, unique = true)
    private String name;

    @Column(name = "Description")
    private String description;

    @ManyToMany(mappedBy="roles")
    private Collection<User> users;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_PRIVILEGES",
            joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "id"))
    private Collection<Privilege> privileges;
}
