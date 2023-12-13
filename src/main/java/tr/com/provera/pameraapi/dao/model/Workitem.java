package tr.com.provera.pameraapi.dao.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import tr.com.provera.pameraapi.dao.common.BaseModel;
import tr.com.provera.pameraapi.enumerate.WorkitemCategory;
import tr.com.provera.pameraapi.enumerate.WorkitemState;
import tr.com.provera.pameraapi.enumerate.WorkitemType;
import tr.com.provera.pameraapi.enumerate.converter.WorkitemCategoryConverter;
import tr.com.provera.pameraapi.enumerate.converter.WorkitemStateConverter;
import tr.com.provera.pameraapi.enumerate.converter.WorkitemTypeConverter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@SuppressWarnings("serial")
@Entity
@Builder

@Table(name = "Workitems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Where(clause = "isdeleted='false'")
@SQLDelete(sql = "UPDATE workitems SET isdeleted = true WHERE id = ? and version = ?")

@EntityListeners(AuditingEntityListener.class)
public class Workitem extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id",nullable = false)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Point")
    private Integer point;

    @Column(name = "DueDate")
    private Date dueDate;

    @Column(name = "ExpectedDate")
    private Date expectedDate;

    @Column(name = "ActualDate")
    private Date actualDate;

    @Column(name = "ResponsibleUser")
    private String responsibleUser;

    @Convert(converter= WorkitemTypeConverter.class)
    @Column(name = "Type")
    private WorkitemType type;

    @Convert(converter= WorkitemCategoryConverter.class)
    @Column(name = "Category")
    private WorkitemCategory category;

    @Convert(converter= WorkitemStateConverter.class)
    @Column(name = "State")
    private WorkitemState state;

    @OneToMany(mappedBy = "workitem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("projectId")
    private Project project;

/*

    // @JsonIgnore
    // @JsonBackReference
    // @JsonIgnore
    // @JsonBackReference
    // @OneToMany(mappedBy = "workitem")
    // private List<Taskitem> taskitems;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projectid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("projectid")
    private Project project;*/
}