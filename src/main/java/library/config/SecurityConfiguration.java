package library.config;

import library.system.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yeyuting
 * @create 2021/3/2
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userDetailService ;

    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder() ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder()) ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/sys/user/**" , "/sys/admin/**" , "/sys/superAdmin/**").hasAnyRole("USER" , "ADMIN" , "SUPERADMIN")
                .antMatchers("/sys/user/**").permitAll()
                .antMatchers("/sys/admin/**").hasRole("ADMIN")
                .antMatchers("/sys/superAdmin/**").hasRole("SUPERADMIN")
                .anyRequest().permitAll()//其他没有限定的请求，允许访问
                .and().anonymous()//对于没有配置权限的其他请求允许匿名访问
                .and().formLogin()//使用 spring security 默认登录页面
                .and().httpBasic();//启用http 基础验证
    }
}
