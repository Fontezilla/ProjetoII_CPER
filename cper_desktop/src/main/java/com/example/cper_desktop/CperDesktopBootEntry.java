package com.example.cper_desktop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {
        "com.example.cper_core",
        "com.example.cper_desktop"
})
public class CperDesktopBootEntry {
}
