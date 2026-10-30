package dev.example.automation.core;

import dev.example.automation.config.TestConfig;
import java.net.URL;

public final class UrlResolver {
    private UrlResolver() { }
    public static String appUrl() {
        String configured = TestConfig.baseUrl();
        if (!configured.startsWith("classpath:")) return configured;
        String path = configured.substring("classpath:".length());
        URL resource = UrlResolver.class.getClassLoader().getResource(path);
        if (resource == null) throw new IllegalStateException("Missing classpath resource: " + path);
        return resource.toExternalForm();
    }
}
