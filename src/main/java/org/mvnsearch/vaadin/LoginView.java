package org.mvnsearch.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
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
        final Account account = new Account();
        account.setNick("linux_china");
        final Binder<Account> binder = new Binder<>();
        binder.forField(userNameField).bind(Account::getNick, Account::setNick);
        binder.forField(passwordField).bind(Account::getPassword, Account::setPassword);
        binder.readBean(account);
        //todo implement event registration here
        loginButton.addClickListener(event -> {
            userNameField.setValue("Jacky");
            try {
                binder.writeBean(account);
            } catch (ValidationException e) {
                e.printStackTrace();
            }
            System.out.println(account.getNick());
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
