package com.chomdan.biz.model.domain;

import com.chomdan.biz.model.domain.enums.AcademySiteDigitalType;
import com.chomdan.biz.model.domain.enums.AcademySiteType;
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
@Table(name = "TB_ACADEMY_SITE_DIGITAL")
public class AcademySiteDigital implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "digital_id")
    @JsonProperty("digital_id")
    private String digitalId;

    // 로고 타입 (이미지, 동영상) -- 동영상은 외부만...
    @Column(name="type")
    @Enumerated(EnumType.ORDINAL)
    private AcademySiteDigitalType type;

    // 컨퍼런스 제목
    @Column(name="title",nullable=false)
    private String title;

    // 내용
    @Column(name="content")
    @Lob
    private String content;

    // VIDEO인 경우 URL
    @Column(name = "video_url")
    private String videoUrl;

    
    @Column(name = "original_name")
    private String originalName;

    @Column(name = "file_size")
    private int fileSize;

    @Column(name = "save_path")
    private String savePath;

    @Column(name = "web_path")
    private String webPath;

    @Column(name = "resolution")
    private String resolution;

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
