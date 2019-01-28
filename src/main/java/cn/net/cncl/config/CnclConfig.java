package cn.net.cncl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CnclConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ErrorInterceptor()).addPathPatterns("/**");
		// registry.addInterceptor(new
		// SessionInterceprot()).addPathPatterns("/AdminCtl/**", "/ClelebritysCtl/**",
		// "/ManagerCtl/**", "/NewsCtl/**", "/NewsTypeCtl/**",
		// "/ImagesCtl/**").excludePathPatterns("/Show/**");

		super.addInterceptors(registry);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("GET", "POST", "DELETE", "PUT").maxAge(3600);
	}
}
