package com.chomdan.biz.model.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by foresight on 17. 8. 2.
 */
@Data
public class TestUserDTO {

    @JsonProperty("user_id")
    private String userId;


}
