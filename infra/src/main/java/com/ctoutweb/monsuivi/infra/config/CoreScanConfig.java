package com.ctoutweb.monsuivi.infra.config;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.ctoutweb.monsuivi.core",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {CoreService.class})}
)
public class CoreScanConfig {
}
