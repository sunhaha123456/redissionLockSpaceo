package com.rose.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="redisson")
public class RedissonProperties {

    private String address;

    private String password;

    private int timeout;

    private int connectionPoolSize;

    private int connectionMinimumIdleSize;
}