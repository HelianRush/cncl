package cn.net.cncl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.net.cncl.common.ErrorInterceptor;

@SpringBootApplication
@MapperScan("cn.net.cncl.mapper")
public class Application extends WebMvcConfigurerAdapter { //

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ErrorInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/show");
		super.addInterceptors(registry);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
