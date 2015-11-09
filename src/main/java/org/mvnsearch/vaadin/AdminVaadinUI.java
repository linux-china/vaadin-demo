package org.mvnsearch.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "")
@Title("Vaadin Admin")
@Theme("valo")
public class AdminVaadinUI extends UI  {
    protected void init(VaadinRequest request) {
        // Create the content root layout for the UI
        VerticalLayout content = new VerticalLayout();
        setContent(content);
        // Display the greeting
        Label label = new Label("Admin!");
        content.addComponent(label);
        // Have a clickable button
        content.addComponent(new Button("Push Me!",
                (Button.ClickListener) e -> {
                    label.setValue("clicked!!!");
                }));
    }

}
