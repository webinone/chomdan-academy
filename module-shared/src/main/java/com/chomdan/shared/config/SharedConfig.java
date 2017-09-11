package com.chomdan.shared.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by foresight on 17. 8. 2.
 */
@Configuration
@PropertySource(value={
        "classpath:property/shared-config.properties"
})
public class SharedConfig {
}
