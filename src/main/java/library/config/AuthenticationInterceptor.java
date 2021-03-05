package library.config;

import com.auth0.jwt.interfaces.DecodedJWT;

import library.system.dto.SysUser;
import library.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yeyuting
 * @create 2021/2/26
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate ;

    @Autowired
    TokenUtil tokenUtil ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token") ;
        if(StringUtils.isEmpty(token)){
            response.sendRedirect("/sys/user/registerUser");
            return false ;
        }
        DecodedJWT jwt = tokenUtil.deToken(token) ;
        String userId =jwt.getClaim("userId").asString() ;
        if(userId==null || userId.trim().equals("")){
            response.sendRedirect("/sys/user/registerUser");
            return false ;
        }
        ValueOperations valueOperations = redisTemplate.opsForValue() ;
        SysUser sysUser = (SysUser)valueOperations.get(userId) ;
        if ((sysUser == null) || (!sysUser.getToken().equals(token))){
            response.sendRedirect("/sys/user/registerUser");
            return false ;
        }
        return true;
    }
}
