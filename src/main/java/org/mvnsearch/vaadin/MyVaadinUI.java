package org.mvnsearch.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;

@SpringUI(path = "/my")
@Title("Vaadin Workspace")
@Theme("valo")
public class MyVaadinUI extends UI {
    protected void init(VaadinRequest request) {
        // Create the content root layout for the UI
        VerticalLayout content = new VerticalLayout();
        setContent(content);
        String nick = (String) getSession().getAttribute("nick");
        if (nick == null) {
            nick = "World";
        }
        // Display the greeting
        Label label = new Label("Hello " + nick + "!");
        content.addComponent(label);
        TextField textField = new TextField("Email");
        content.addComponent(textField);
        // Have a clickable button
        content.addComponent(new Button("Push Me!",
                (Button.ClickListener) e -> {
                    label.setValue(textField.getValue());
                }));
    }

}
