package ru.Technopolis;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/create").setViewName("create");
        registry.addViewController("/read").setViewName("read");
        registry.addViewController("/update").setViewName("update");
        registry.addViewController("/delete").setViewName("delete");
        registry.addViewController("/login").setViewName("login");
    }

}
