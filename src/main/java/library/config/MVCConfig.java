package library.config;


import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yeyuting
 * @create 2021/2/26
 */
//@Configuration
//@AutoConfigureBefore(SecurityConfiguration.class)
public class MVCConfig implements WebMvcConfigurer {
    @Bean
    public HandlerInterceptor authenticationInterceptor(){
        return  new AuthenticationInterceptor () ;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/sys/user/*")
                .addPathPatterns("/sys/admin/*")
                .addPathPatterns("/sys/superAdmin/*")
                .excludePathPatterns("/sys/user/registerUser")
                .excludePathPatterns("/sys/admin/login")
                .excludePathPatterns("/sys/superAdmin/login")
                .excludePathPatterns("/sys/user/login") ;
    }


}
