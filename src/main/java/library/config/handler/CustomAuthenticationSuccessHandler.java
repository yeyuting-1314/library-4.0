package library.config.handler;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.system.dao.UserMapper;
import library.system.dto.SysUser;
import library.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeyuting
 * @create 2021/3/4
 */
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    TokenUtil tokenUtil ;

    @Autowired
    RedisTemplate redisTemplate ;

    @Autowired
    UserMapper userMapper ;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        Object principal = auth.getPrincipal();
        String userName = auth.getName() ;
        ValueOperations valueOperations = redisTemplate.opsForValue() ;
        //String userJson = (String) valueOperations.get(userName) ;
        //SysUser newUser = (SysUser) JSONArray.parseArray(userJson , SysUser.class) ;
        SysUser newUser = (SysUser) valueOperations.get(userName) ;
        String token = newUser.getToken();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        response.setStatus(200);
        Map<String, Object> map = new HashMap<>(16);
        map.put("status", 200);
        map.put("msg", principal);
        map.put("token", token);
        map.put("privilege", newUser.getPrivilegeList());

        ObjectMapper om = new ObjectMapper();
        out.write(om.writeValueAsString(map));
        out.flush();
        out.close();

    }
}
