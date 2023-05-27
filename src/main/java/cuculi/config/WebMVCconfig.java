package cuculi.config;

import cuculi.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class WebMVCconfig extends WebMvcConfigurationSupport {
    /**
     * 解决后端资源访问问题
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        log.info("开始进行静态资源映射");
//        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
//        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    /**
     * 配置跨域问题，允许前端访问后端
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 设置允许跨域的路径
                .allowedOrigins("http://localhost:5173")  // 设置允许跨域请求的源
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 设置允许的HTTP方法
                .allowedHeaders("Content-Type", "Authorization", "Access-Control-Allow-Origin")  // 设置允许的请求头
                .allowCredentials(true);  // 允许发送身份凭证（如cookies）

        // 其他配置...
    }

    /**
     * 添加扩展的JSON转换器，方便序列化
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new JacksonObjectMapper());
        converters.add(0, converter);
    }
}
