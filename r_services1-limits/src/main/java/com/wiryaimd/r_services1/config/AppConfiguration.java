package com.wiryaimd.r_services1.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("limit-service") // agar automatically membaca configuration pada properties file dengan prefix limit-service, dan mengambil valuenya sesuai nama field yg sama
public class AppConfiguration {

    private int minimum; // ini nama field yg sama dengan property di properties file
    private int maximum;

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMinimum(int minimum) { // penggunaan annot configuration properties juga memerlukan setter agar bisa di set datanya
        this.minimum = minimum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
