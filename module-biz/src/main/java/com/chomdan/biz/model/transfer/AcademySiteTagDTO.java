package com.chomdan.biz.model.transfer;

import com.chomdan.biz.model.domain.AcademySite;
import com.chomdan.biz.model.domain.AcademySiteTag;
import com.chomdan.biz.model.domain.AcademySiteThumbnail;
import com.chomdan.biz.model.domain.enums.AcademySiteType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by foresight on 17. 7. 31.
 */
@Data
public class AcademySiteTagDTO extends BaseDTO  {

    @JsonProperty("tag_id")
    private String tagId;

    @JsonProperty("name")
    private String name;


}
