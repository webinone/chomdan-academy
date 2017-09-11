package com.chomdan.biz.model.transfer;

import com.chomdan.biz.model.domain.AcademySiteProgram;
import com.chomdan.biz.model.domain.enums.AcademyMainBannerType;
import com.chomdan.biz.model.domain.enums.AcademySiteType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foresight on 17. 7. 31.
 */
@Data
public class AcademyMainBannerDTO extends BaseDTO  {

    private Long idx;

    private String bannerType;

    @NotNull
    @NotEmpty
    private String title;

    private String description;

    private String originalName;

    @JsonProperty(value = "file_size")
    private int fileSize;

    private String savePath;

    private String webPath;

    private String linkUrl;
}
