package org.mvnsearch.vaadin.boot.management;

import java.lang.annotation.*;

/**
 * spring mangement only annotation
 *
 * @author linux_china
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SpringManagementOnly {
}
