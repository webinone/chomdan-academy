package com.chomdan.biz.model.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by foresight on 17. 7. 24.
 */
@Data
@ToString(exclude = {"academySite"})
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idx")
@Table(name = "TB_ACADEMY_SITE_PROGRAM")
public class AcademySiteProgram implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "program_id")
    @JsonProperty("program_id")
    private String programId;

    @Column(name="title",nullable=false)
    private String title;

    @Column(name="content")
    @Lob
    private String content;

    @Column(name = "program_order")
    private int programOrder;

    @ManyToOne
    @JoinColumn(name="academy_site_id", referencedColumnName = "academy_site_id")
    @JsonIgnore
    private AcademySite academySite;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

}
