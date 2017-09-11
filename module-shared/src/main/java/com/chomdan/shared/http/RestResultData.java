package com.chomdan.shared.http;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Created by foresight on 17. 7. 31.
 */
//@Data
public class RestResultData {

    private boolean success;

    private HttpStatus resultCode;

    private Object resultData;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HttpStatus getResultCode() {
        return resultCode;
    }

    public void setResultCode(HttpStatus resultCode) {
        this.resultCode = resultCode;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }
}
