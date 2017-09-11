package com.chomdan.biz.model.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * Created by foresight on 17. 7. 31.
 */
//@Data
public class FileInfoDTO extends BaseDTO {

  //  private String 

    @NotNull
    private Long idx;

    @JsonProperty("program_id")
    private String programId;

    @JsonProperty("academy_site_id")
    private String academySiteId;

    private String title;

    private String content;

    private String description;


}
