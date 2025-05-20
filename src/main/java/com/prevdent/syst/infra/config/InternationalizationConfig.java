package com.prevdent.syst.infra.config;


import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CountryResponse;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Configuration
public class InternationalizationConfig {

    private static final Map<String, Locale> COUNTRY_TO_LOCALE = new HashMap<>();

    static {
        COUNTRY_TO_LOCALE.put("BR", new Locale("pt", "BR"));
        COUNTRY_TO_LOCALE.put("PT", new Locale("pt", "PT"));
        COUNTRY_TO_LOCALE.put("ES", new Locale("es", "ES"));
    }

    @Bean
    public LocaleResolver localeResolver() {
        SmartLocaleResolver resolver = new SmartLocaleResolver();
        resolver.setDefaultLocale(new Locale("pt", "BR"));
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    public static class SmartLocaleResolver extends AcceptHeaderLocaleResolver {

        private DatabaseReader geoIpReader;

        @PostConstruct
        public void init() throws IOException {
            File database = new File("src/main/resources/geolite2/GeoLite2-Country.mmdb");
            if (database.exists()) {
                this.geoIpReader = new DatabaseReader.Builder(database).build();
            }
        }

        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            if (request.getParameter("lang") != null) {
                return new Locale(request.getParameter("lang"));
            }

            Locale browserLocale = request.getLocale();

            Locale geoLocale = detectLocaleFromIp(request);

            if (geoLocale != null && !geoLocale.equals(browserLocale)) {
                return geoLocale;
            }

            return browserLocale != null ? browserLocale : getDefaultLocale();
        }

        private Locale detectLocaleFromIp(HttpServletRequest request) {
            if (geoIpReader == null) {
                return null;
            }

            try {
                String ip = getClientIp(request);
                if (ip == null || ip.isEmpty() || ip.equals("127.0.0.1")) {
                    return null;
                }

                InetAddress ipAddress = InetAddress.getByName(ip);
                CountryResponse response = geoIpReader.country(ipAddress);
                String countryCode = response.getCountry().getIsoCode();

                return COUNTRY_TO_LOCALE.getOrDefault(countryCode, null);
            } catch (Exception e) {
                return null;
            }
        }

        private String getClientIp(HttpServletRequest request) {
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }
    }
}
