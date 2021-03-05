package library.config.handler;

import com.alibaba.fastjson.JSONObject;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import library.base.Result;
import library.util.Results;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        Result result = Results.failure("403", "没有权限");
        resp.setContentType("application/json;charset=UTF-8");
        out.println(JSONObject.toJSONString(result));
        out.flush();
        out.close();
    }
}
