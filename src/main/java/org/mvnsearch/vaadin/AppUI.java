package org.mvnsearch.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;

/**
 * App UI
 *
 * @author linux_china
 */
@SpringUI
@Title("Welcome to Application ")
@Theme("valo")
@SpringViewDisplay
@Widgetset("AppWidgetset")
public class AppUI extends UI implements ViewDisplay {
    private Panel springViewDisplay;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
    }

    private void setupLayout() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        setContent(layout);
        springViewDisplay = new Panel();
        springViewDisplay.setWidth("50%");
        layout.addComponent(springViewDisplay);
        layout.setComponentAlignment(springViewDisplay, Alignment.TOP_CENTER);
    }

    @Override
    public void showView(View view) {
        if (view instanceof Component) {
            springViewDisplay.setContent((Component) view);
        }
    }
}
