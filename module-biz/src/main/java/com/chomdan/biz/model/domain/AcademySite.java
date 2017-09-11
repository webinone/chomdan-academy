package com.chomdan.biz.model.domain;

import com.chomdan.biz.model.domain.enums.AcademySiteType;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by foresight on 17. 7. 24.
 */
@Data
@ToString(exclude = {"academySiteTags", "academySitePrograms", "academySiteBanners", "academySiteDigitals"})
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "idx")
@Table( name = "TB_ACADEMY_SITE")
//@Table( name = "TB_ACADEMY_SITE",
//        indexes = {
//            @Index(name = "idx_academy_site01", columnList = "academy_site_id", unique = false)
//        })
public class AcademySite implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "academy_site_id", nullable = false)
    private String academySiteId;

    // 사이트 타입 : 세미나컨퍼런스/교육
    @Column(name="type")
//    @Enumerated(EnumType.ORDINAL)
    private int type;


    @Column(name="title",nullable=false)
    private String title;

    // 부제
    @Column(name="sub_title")
    private String subTitle;

    // 시작일
    @Temporal(TemporalType.DATE)
    @Column(name="start_date")
    private Date startDate;

    // 종료일
    @Temporal(TemporalType.DATE)
    @Column(name="end_date")
    private Date endDate;

    // 시작시간
    @Column(name="start_time")
    private String startTime;

    // 종료시간
    @Column(name="end_time")
    private String endTime;

    // 주최 정보
    @Column(name = "host_info")
    private String hostInfo;

    // 태그
    @OneToMany(mappedBy = "academySite", cascade = CascadeType.ALL)
    @JsonProperty("tags")
    private List<AcademySiteTag> academySiteTags;

    // 설명
    @Column(name="description")
    @Lob
    private String description;

    // 주소
    @Column(name = "address")
    private String address;

    // 장소
    @Column(name = "location")
    private String location;

    // 위도 latitude
    @Column(name = "latitude")
    private String latitude;

    // 경도 longitude
    @Column(name = "longitude")
    private String longitude;

    // 대중교통 transportation
    @Column(name = "transportation")
    private String transportation;

    // 승용차
    @Column(name = "car")
    private String car;

    // 사전 등록기간
    // ----------------------------------------------------------
    @Temporal(TemporalType.DATE)
    @Column(name="pre_regist_start_date")
    private Date preRegistStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name="pre_regist_end_date")
    private Date preRegistEndDate;

    //---------------------------------------------------------

    // 사전 등록금액
    // ----------------------------------------------------------
    @Column(name = "pre_regist_amount")
    private String preRegistAmount;
    // ----------------------------------------------------------

    // 현장 등록금액
    // ----------------------------------------------------------
    @Column(name = "offline_amount")
    private String offLineAmount;
    // ----------------------------------------------------------

    // 담당자명
    @Column(name = "admin_name")
    private String adminName;

    // 담당자 직급/직책
    @Column(name = "admin_position")
    private String adminPosition;

    @Column(name = "admin_phone")
    private String adminPhone;

    // 담당자 이메일 정보
    @Column(name = "admin_email")
    private String adminEmail;

    //----------------------------------------------------------------

//    @Column(name="thumbnail_id")
//    private String thumbnailId;

    // 썸네일
    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="thumbnail_id", referencedColumnName = "thumbnail_id")
    @JsonProperty("thumbnail")
    private AcademySiteThumbnail academySiteThumbnail;

    // 배너
    @OneToMany(fetch=FetchType.LAZY, mappedBy = "academySite")
    @JsonIgnore
    private List<AcademySiteBanner> academySiteBanners = new ArrayList<AcademySiteBanner>();
//
    // 프로그램
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academySite")
    @JsonProperty("programs")
    private List<AcademySiteProgram> academySitePrograms = new ArrayList<AcademySiteProgram>();

    // 디지털 컨퍼런스
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academySite")
    @JsonProperty("digitals")
    private List<AcademySiteDigital> academySiteDigitals = new ArrayList<AcademySiteDigital>();


    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;


}