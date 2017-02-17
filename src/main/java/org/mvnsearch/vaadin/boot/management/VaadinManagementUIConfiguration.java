package org.mvnsearch.vaadin.boot.management;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * vaadin management UI configuration
 *
 * @author linux_china
 */
@Configuration
public class VaadinManagementUIConfiguration {
    @Value("${spring.vaadin.management.uris}")
    private String uris;
    @Value("${server.port}")
    private String serverPort;
    @Value("${management.port}")
    private String managementPort;
    @Value("${spring.vaadin.management.path:/vaadin-admin}")
    private String vaadinAdminPath;

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ManagementRedirectFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("server.port", serverPort);
        registration.addInitParameter("management.port", managementPort);
        registration.addInitParameter("vaddin.management.path", vaadinAdminPath);
        registration.addInitParameter("uiPaths", uris);
        registration.setName("ManagementRedirectFilter");
        registration.setOrder(1);
        return registration;
    }
}
