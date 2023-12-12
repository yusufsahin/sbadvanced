package tr.com.provera.pameraapi.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tr.com.provera.pameraapi.dao.common.BaseModel;
import tr.com.provera.pameraapi.dao.model.User;

import java.util.Date;

@SuppressWarnings("serial")
@Builder

@Entity
@Table(name = "RefreshToken")

@Data
@NoArgsConstructor
@AllArgsConstructor

// refresh token için gerekli mi?
@Where(clause = "isdeleted='false'")
@SQLDelete(sql = "UPDATE projects SET isdeleted = true WHERE id = ? and version = ?")

@EntityListeners(AuditingEntityListener.class)
public class RefreshToken extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @Column(name = "Token")
    private String token;

    @Column(name = "ExpiryDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;


}
