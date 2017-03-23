package org.mvnsearch.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VaadinDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaadinDemoApplication.class, args);
    }

    @SpringView(name = "")
    public class EmptyView implements View {
        @Override
        public void enter(ViewChangeListener.ViewChangeEvent event) {

        }
    }

}
