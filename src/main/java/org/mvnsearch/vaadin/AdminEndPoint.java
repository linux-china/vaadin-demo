/*
 * Copyright 2000-2014 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.mvnsearch.vaadin;

import com.vaadin.server.Constants;
import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.spring.server.SpringVaadinServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.actuate.autoconfigure.EndpointWebMvcAutoConfiguration;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ServletWrappingController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * Provides various helper methods for connectors. Meant for internal use.
 *
 * @author Vaadin Ltd
 */

@Component
@AutoConfigureAfter(EndpointWebMvcAutoConfiguration.class)
public class AdminEndPoint implements MvcEndpoint, ServletContextAware, InitializingBean, ApplicationContextAware {
    public static final String ADMIN_PATH = "/admin";
    private final ServletWrappingController servletController = new ServletWrappingController();

    public AdminEndPoint() {
        servletController.setServletClass(ManagementVaadinServlet.class);
        servletController.setServletName("VaadinAdmin");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("UI", AdminVaadinUI.class.getName());
        servletController.setInitParameters(properties);
        servletController.afterPropertiesSet();
    }

    @Override
    public String getPath() {
        return ADMIN_PATH;
    }

    @Override
    public boolean isSensitive() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        servletController.setApplicationContext(applicationContext);
    }

    @Override
    public Class<? extends Endpoint> getEndpointType() {
        return null;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        servletController.setServletContext(servletContext);
    }

    @RequestMapping("**")
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request = new HttpServletRequestWrapper(request) {
            @Override
            public String getPathInfo() {
                return stripAdminPrefix(super.getPathInfo());
            }

            @Override
            public String getRequestURI() {
                return stripAdminPrefix(super.getRequestURI());
            }

            private String stripAdminPrefix(String pathInfo) {
                if (pathInfo != null && pathInfo.startsWith(ADMIN_PATH)) {
                    pathInfo = pathInfo.substring(ADMIN_PATH.length());
                }
                return pathInfo;
            }
        };
        return servletController.handleRequest(request, response);
    }

    public static class ManagementVaadinServlet extends SpringVaadinServlet {
        public ManagementVaadinServlet() {
            super();
            setServiceUrlPath(ADMIN_PATH);
        }

        @Override
        protected DeploymentConfiguration createDeploymentConfiguration(Properties initParameters) {
            Properties properties = new Properties(initParameters);
            properties.setProperty(Constants.PARAMETER_VAADIN_RESOURCES, ADMIN_PATH);
            return super.createDeploymentConfiguration(properties);
        }
    }
}
