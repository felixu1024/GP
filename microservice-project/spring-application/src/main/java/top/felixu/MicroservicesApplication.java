package top.felixu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author felixu
 * @Date 2018.07.26
 */
@SpringBootApplication
public class MicroservicesApplication {

    public static void main(String[] args) {
//        SpringApplication.run(MicroservicesApplication.class, args);
//        new SpringApplicationBuilder(MicroservicesApplication.class)
//                .properties("server.port=0")
//                .run(args);
        SpringApplication springApplication = new SpringApplication(MicroservicesApplication.class);
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("server.port", "0");
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);
    }
}
