package com.excellent.accreditation.config;

import com.excellent.accreditation.config.properties.SwaggerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "web")
public class WebProperties {

    private boolean openAopLog = true;

    private SwaggerProperties swagger = new SwaggerProperties();
}
