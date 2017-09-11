package com.chomdan.biz.model.transfer;

import com.chomdan.biz.model.domain.enums.AcademySiteDigitalType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

/**
 * Created by foresight on 17. 7. 31.
 */
@Data
public class AcademySiteDigitalDTO extends BaseDTO  {

    @JsonProperty("academy_site_id")
    private String academySiteId;

    @JsonProperty("digital_id")
    private String digitalId;

    @JsonProperty("type")
    private String type;

    // 컨퍼런스 제목
    @JsonProperty("title")
    private String title;

    // 내용
    @JsonProperty("content")
    private String content;

    // VIDEO인 경우 URL
    @JsonProperty("video_url")
    private String videoUrl;

    @JsonProperty("original_name")
    private String originalName;

    @JsonProperty("save_path")
    private String savePath;

    @JsonProperty("web_path")
    private String webPath;

}
