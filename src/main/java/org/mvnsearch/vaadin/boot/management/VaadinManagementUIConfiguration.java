package org.mvnsearch.vaadin.boot.management;

import com.vaadin.spring.annotation.SpringUI;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * vaadin management UI configuration
 *
 * @author linux_china
 */
@Configuration
public class VaadinManagementUIConfiguration implements ApplicationContextAware {
    private List<String> uris = new ArrayList<>();
    @Value("${server.port}")
    private String serverPort;
    @Value("${management.port}")
    private String managementPort;
    @Value("${spring.vaadin.management.path:/vaadin-admin}")
    private String vaadinAdminPath;

    /**
     * find all Vaadin UI with @SpringManagementOnly
     *
     * @param applicationContext application context
     * @throws BeansException bean exception
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> apps = applicationContext.getBeansWithAnnotation(SpringBootConfiguration.class);
        for (Object app : apps.values()) {
            Class appClazz = app.getClass();
            if (AopUtils.isAopProxy(app)) {
                appClazz = AopUtils.getTargetClass(app).getClass();
            }
            String packageName = appClazz.getPackage().getName();
            ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
            provider.addIncludeFilter(new AnnotationTypeFilter(SpringManagementOnly.class));
            Set<BeanDefinition> vaadinComponents = provider.findCandidateComponents(packageName);
            for (BeanDefinition beanDefinition : vaadinComponents) {
                String beanClassName = beanDefinition.getBeanClassName();
                try {
                    SpringUI springUI = AnnotationUtils.getAnnotation(Class.forName(beanClassName), SpringUI.class);
                    uris.add(springUI.path());
                } catch (Exception ignore) {

                }
            }
        }

    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ManagementRedirectFilter());
        registration.addServletNames("springVaadinServlet");
        registration.addInitParameter("server.port", serverPort);
        registration.addInitParameter("management.port", managementPort);
        registration.addInitParameter("vaddin.management.path", vaadinAdminPath);
        registration.addInitParameter("uris", String.join(",", uris));
        registration.setName("ManagementRedirectFilter");
        registration.setOrder(1);
        return registration;
    }
}
