package org.mvnsearch.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;

import javax.annotation.PostConstruct;

/**
 * login view
 *
 * @author linux_china
 */
@SpringView(name = LoginView.name)
public class LoginView extends LoginDesign implements View {

    public final static String name = "loginview";

    @PostConstruct
    public void init() {
        //todo implement event registration here
        loginButton.addClickListener(event -> {
            userNameField.setValue("Jacky");
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
