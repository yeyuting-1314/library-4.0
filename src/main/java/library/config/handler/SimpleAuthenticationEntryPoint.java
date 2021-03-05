package library.config.handler;

import com.alibaba.fastjson.JSONObject;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import library.base.Result;
import library.util.Results;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yeyuting
 * @create 2021/3/4
 */
@Component
public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Result result = Results.failure("401", "请登陆");
        response.setContentType("application/json;charset=UTF-8");
        out.println(JSONObject.toJSONString(result));
        out.flush();
        out.close();
    }
}
