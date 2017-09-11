package com.chomdan.shared.http.interceptor;

import com.chomdan.shared.http.RestResultData;
import com.chomdan.shared.security.jwt.TokenAdminProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by foresight on 17. 8. 2.
 */
public class TokenAdminInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TokenAdminProvider tokenAdminProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("======================== TokenAdminInterceptor preHandle ======================== ");

        String tokenString = StringUtils.trimToEmpty(request.getHeader("Authorization"));

        if (!"".equals(tokenString)) {
            logger.debug(tokenString);

            if (this.tokenAdminProvider.isValid(tokenString)) {
                return true;
            }
        }

        RestResultData restResultData = new RestResultData();
        restResultData.setResultCode(HttpStatus.UNAUTHORIZED);
        restResultData.setSuccess(false);
        restResultData.setResultData("Authorization Error");

        ObjectMapper mapper = new ObjectMapper();

//        String jsonErrorString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(restResultData);
        String jsonErrorString = "Authorization Error";

//        response.setHeader("Content-Type","application/json");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, jsonErrorString);

        return false;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {

    }

}
