package com.chomdan.biz.model.transfer;

import com.chomdan.biz.model.domain.AcademySiteThumbnail;
import com.chomdan.biz.model.domain.enums.AcademySiteType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by foresight on 17. 7. 31.
 */
@Data
public class AcademySiteDTO extends BaseDTO  {

    private Long idx;

    @JsonProperty("academy_site_id")
    private String academySiteId;

    @JsonProperty("type")
    private String type;

    @NotNull
    @NotEmpty
    private String title;

//    @JsonProperty("sub_title")
    private String subTitle;

//    @JsonProperty("start_date")
    private Date startDate;

//    @JsonProperty("end_date")
    private Date endDate;

    // 시작시간
//    @JsonProperty("start_time")
    private String startTime;

    // 종료시간
//    @JsonProperty("end_time")
    private String endTime;

    // 주최 정보
//    @JsonProperty("host_info")
    private String hostInfo;

    // 썸네일
    @JsonProperty("thumbnail")
    private AcademySiteThumbnailDTO academySiteThumbnailDTO;

    // 태그
    @JsonProperty("tags")
    private List<AcademySiteTagDTO> academySiteTagDTOs = new ArrayList<AcademySiteTagDTO>();

    // 설명
    private String description;

    // 주소
    @JsonProperty("address")
    private String address;

    // 장소
    @JsonProperty("location")
    private String location;

    // 위도 latitude
    @JsonProperty("latitude")
    private String latitude;

    // 경도 longitude
    @JsonProperty("longitude")
    private String longitude;

    // 대중교통 transportation
    @JsonProperty("transportation")
    private String transportation;

    // 승용차
    @JsonProperty("car")
    private String car;

    // 사전 등록기간
    // ----------------------------------------------------------
//    @JsonProperty("pre_regist_start_date")
    private Date preRegistStartDate;

//    @JsonProperty("pre_regist_end_date")
    private Date preRegistEndDate;

    // 사전 등록금액
    // ----------------------------------------------------------
//    @JsonProperty("pre_regist_amount")
    private String preRegistAmount;
    // ----------------------------------------------------------

    // 현장 등록금액
    // ----------------------------------------------------------
//    @JsonProperty("offline_amount")
    private String offLineAmount;
    // ----------------------------------------------------------

    // 담당자명
//    @JsonProperty("admin_name")
    private String adminName;

    // 담당자 직급/직책
//    @JsonProperty("admin_position")
    private String adminPosition;

//    @JsonProperty("admin_phone")
    private String adminPhone;

    // 담당자 이메일 정보
//    @JsonProperty("admin_email")
    private String adminEmail;

    //----------------------------------------------------------------

}
