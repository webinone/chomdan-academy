package com.chomdan.biz.model.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by foresight on 17. 7. 31.
 */
@Data
public class AcademySiteJoinDTO extends BaseDTO  {

    @JsonProperty("join_id")
    private String joinId;

    @JsonProperty("member_id")
    private String memberId;

    @JsonProperty("member_name")
    private String memberName;

    @JsonProperty("phone_no")
    private String phoneNo;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("position")
    private String position;

    @JsonProperty("email")
    private String email;

    
}
