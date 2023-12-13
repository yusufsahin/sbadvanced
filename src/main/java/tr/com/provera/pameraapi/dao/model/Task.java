package tr.com.provera.pameraapi.dao.model;


import tr.com.provera.pameraapi.dao.common.BaseModel;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import tr.com.provera.pameraapi.enumerate.TaskCategory;
import tr.com.provera.pameraapi.enumerate.TaskStatus;
import tr.com.provera.pameraapi.enumerate.TaskType;
import tr.com.provera.pameraapi.enumerate.converter.TaskCategoryConverter;
import tr.com.provera.pameraapi.enumerate.converter.TaskStatusConverter;
import tr.com.provera.pameraapi.enumerate.converter.TaskTypeConverter;

import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Builder

@Table(name = "Tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Where(clause = "isDeleted=false")
@SQLDelete(sql = "UPDATE Tasks SET isdeleted = true WHERE id = ? and version = ?")

@EntityListeners(AuditingEntityListener.class)
public class Task extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id",nullable = false)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "DueDate")
    private Date dueDate;

    @Column(name = "ExpectedDate")
    private Date expectedDate;

    @Column(name = "ActualDate")
    private Date actualDate;

    @Column(name = "HoursExpected")
    private Integer hoursExpected;

    @Column(name = "HoursActual")
    private Integer hoursActual;

    @Column(name = "AssignTo")
    private String assignTo;

    @Convert(converter= TaskTypeConverter.class)
    @Column(name = "Type")
    private TaskType type;

    @Convert(converter= TaskCategoryConverter.class)
    @Column(name = "Category")
    private TaskCategory category;


    @Convert(converter= TaskStatusConverter.class)
    @Column(name = "Status")
    private TaskStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workitemid", nullable = false)
    private Workitem workitem;


/*
    //Workitem oluşturulunca import edilecek
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workitemid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("workitemid")
    private Workitem workitem;
*/
}
