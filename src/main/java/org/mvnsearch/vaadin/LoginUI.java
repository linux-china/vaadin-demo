package org.mvnsearch.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * login UI
 *
 * @author linux_china
 */
@SpringUI(path = "/login")
@Title("Vaadin Login")
@Theme("valo")
public class LoginUI extends UI {
    @Autowired
    private SpringViewProvider viewProvider;
    private VerticalLayout layout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        setupNavigator();
        getUI().getNavigator().navigateTo(LoginView.name);
    }

    private void setupLayout() {
        layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
    }

    private void setupNavigator() {
        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        layout.addComponent(viewContainer);
        layout.setExpandRatio(viewContainer, 1.0f);
        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
    }
}
