package com.chomdan.biz.model.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idx")
@Table(name = "TB_ACADEMY_SITE_JOIN")
public class AcademySiteJoin implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "join_id")
    @JsonProperty("join_id")
    private String joinId;

    // 참여자 로그인 했을 경우...
    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "position")
    private String position;

    @Column(name = "email")
    private String email;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
    
}
