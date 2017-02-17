package org.mvnsearch.vaadin.boot.management;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * management uri redirect filter
 *
 * @author linux_china
 */
public class ManagementRedirectFilter implements Filter {
    private Integer managementPort;
    private List<String> uiPaths;
    private String vaadinAdminPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        managementPort = Integer.valueOf(filterConfig.getInitParameter("management.port"));
        vaadinAdminPath = filterConfig.getInitParameter("vaddin.management.path");
        String path = filterConfig.getInitParameter("uiPaths");
        if (path != null && !path.isEmpty()) {
            uiPaths = Arrays.asList(path.split(","));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        if (uiPaths!=null && uiPaths.contains(requestURI)) {
            String redirectUrl = request.getRequestURL().toString().replace(requestURI, vaadinAdminPath + requestURI);
            if (redirectUrl.contains(":")) {
                redirectUrl = redirectUrl.replaceFirst(":\\d+", ":" + managementPort);
            } else {
                int slashIndex = redirectUrl.indexOf("/", 8);
                redirectUrl = redirectUrl.substring(0, slashIndex) + ":" + managementPort + redirectUrl.substring(slashIndex);
            }
            if (request.getQueryString() != null && !request.getQueryString().isEmpty()) {
                redirectUrl = redirectUrl + "?" + request.getQueryString();
            }
            ((HttpServletResponse) servletResponse).sendRedirect(redirectUrl);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
