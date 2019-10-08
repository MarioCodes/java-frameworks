package es.msanchez.spring.springinaction.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "es.msanchez.spring.springinaction.**"
})
public class SpringConfig {
}
