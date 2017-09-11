package com.chomdan.shared.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by foresight on 17. 7. 26.
 */
public class ResponseEntityUtil {



   public static RestResultData resultData(boolean isSucess, Object data, HttpStatus status) {

       RestResultData restResultData = new RestResultData();

       restResultData.setSuccess(isSucess);
       restResultData.setResultCode(status);
       restResultData.setResultData(data);

       return restResultData;
   }




}
