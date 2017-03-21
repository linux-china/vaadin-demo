package org.mvnsearch.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;

/**
 * login UI
 *
 * @author linux_china
 */
@SpringUI(path = "/login")
@Title("Vaadin Login")
@Theme("valo")
@SpringViewDisplay
public class LoginUI extends UI implements ViewDisplay {
    private Panel springViewDisplay;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        getUI().getNavigator().navigateTo(LoginView.name);
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
        springViewDisplay.setContent((Component) view);
    }
}
