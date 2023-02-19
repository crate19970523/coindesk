package com.cathaybk.coindesk.interceptor;

import com.cathaybk.coindesk.dao.TbAccountKeyDao;
import com.cathaybk.coindesk.exception.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private TbAccountKeyDao accountKeyDao;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String apiKey = Optional.ofNullable(request.getHeader("Api-key"))
                .orElseThrow(() -> new AuthException("Api-key can't not be empty"));
        if (!Optional.ofNullable(accountKeyDao.findByApiKey(apiKey)).isPresent()) {
            throw new AuthException("api-key does not exist in the system, please contact IT team");
        } else {
            return true;
        }
    }

    @Autowired
    public AuthInterceptor setAccountKeyDao(TbAccountKeyDao accountKeyDao) {
        this.accountKeyDao = accountKeyDao;
        return this;
    }
}
