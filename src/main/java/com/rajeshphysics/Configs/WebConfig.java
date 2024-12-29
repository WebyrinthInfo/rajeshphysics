//package com.rajeshphysics.Configs;
//
//import java.io.File;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class WebConfig implements WebMvcConfigurer {
//
//    @Value("${app.banner-folder}")
//    private String bannerFolder; // Directory where banners are stored
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // Serve static files from the banner folder directory on the file system
//        registry
//            .addResourceHandler("/banners/**")  // Access banners through /banners URL
//            .addResourceLocations("file:" + bannerFolder + File.separator); // Ensure correct path to the banner folder
//    }
//}
//
