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
@JsonIdentityInfo(
//        generator = ObjectIdGenerators.IntSequenceGenerator.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idx")
@Table(name = "TB_ACADEMY_SITE_THUMBNAIL")
public class AcademySiteThumbnail implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "thumbnail_id")
    @JsonProperty("thumbnail_id")
    private String thumbnailId;

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

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

}
