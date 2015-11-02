package org.mvnsearch.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;

@SpringUI(path = "/demo")
@Title("Vaadin Demo")
@Theme("valo")
public class MyVaadinUI extends UI {
    protected void init(VaadinRequest request) {
        // Create the content root layout for the UI
        VerticalLayout content = new VerticalLayout();
        setContent(content);
        // Display the greeting
        Label label = new Label("Hello World!");
        content.addComponent(label);
        // Have a clickable button
        content.addComponent(new Button("Push Me!",
                (Button.ClickListener) e -> {
                    label.setValue("clicked!!!");
                }));
    }

}
