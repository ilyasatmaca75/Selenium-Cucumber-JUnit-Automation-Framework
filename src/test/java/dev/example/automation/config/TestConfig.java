package dev.example.automation.config;

import java.time.Duration;
import java.util.Locale;

public final class TestConfig {
    private TestConfig() { }
    public static String read(String name, String defaultValue) {
        String fromProperty = System.getProperty(name);
        if (fromProperty != null && !fromProperty.isBlank()) return fromProperty;
        String fromEnvironment = System.getenv(name.toUpperCase(Locale.ROOT).replace('.', '_'));
        return fromEnvironment == null || fromEnvironment.isBlank() ? defaultValue : fromEnvironment;
    }
    public static String browser() { return read("browser", "chrome").toLowerCase(Locale.ROOT); }
    public static boolean headless() { return Boolean.parseBoolean(read("headless", "true")); }
    public static Duration timeout() { return Duration.ofSeconds(Long.parseLong(read("timeout.seconds", "10"))); }
    public static String baseUrl() { return read("base.url", "classpath:demo/login.html"); }
}
