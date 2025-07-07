package com.example.cper_desktop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {
        "com.example.cper_core",
        "com.example.cper_desktop"
})
public class CperDesktopBootEntry {
    @Bean
    public CommandLineRunner printBeans(ApplicationContext ctx) {
        return args -> {
            System.out.println("📦 Beans registados:");
            for (String beanName : ctx.getBeanDefinitionNames()) {
                if (beanName.toLowerCase().contains("perfil")) {
                    System.out.println("➡️ " + beanName);
                }
            }
        };
    }
}






