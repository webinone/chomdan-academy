package com.chomdan.biz.model.transfer;

import com.chomdan.biz.model.domain.enums.AcademySiteType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * Created by foresight on 17. 7. 31.
 */
@Data
public class AcademySiteLogoDTO extends BaseDTO  {

    @JsonProperty("logo_id")
    private String logoId;

    // 0 : TEXT , 1 : IMAGE
    @JsonProperty(value = "logo_type")
    private String logoType;

    // 로고 타이틀
    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "save_path")
    private String savePath;

    @JsonProperty(value = "web_path")
    private String webPath;

    
}
