package com.chomdan.biz.model.domain;

import com.chomdan.biz.model.domain.enums.AcademyMainBannerType;
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
@Table(name = "TB_ACADEMY_MAIN_BANNER")
public class AcademyMainBanner implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "academy_main_id")
    private String academyMainId;

    @Column(name = "banner_id")
    private String bannerId;

    @Column(name = "type")
    private String bannerType; //0 :  main rolling / 1 : 고정 영역
//    private AcademyMainBannerType academyMainBannerType;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "banner_order")
    private int bannerOrder;

    @Column(name = "file_size")
    private int fileSize;

    @Column(name = "save_path")
    private String savePath;

    @Column(name = "web_path")
    private String webPath;

    @Column(name = "link_url")
    private String linkUrl;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

}
