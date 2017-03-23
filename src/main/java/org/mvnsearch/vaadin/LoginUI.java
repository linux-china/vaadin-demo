package org.mvnsearch.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

/**
 * login UI
 *
 * @author linux_china
 */
@SpringUI(path = "/login")
@Title("Vaadin Login")
@Theme("valo")
public class LoginUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
    }

    private void setupLayout() {
        LoginDesign design = new LoginDesign();
        final Account account = new Account();
        account.setNick("linux_china");
        final Binder<Account> binder = new Binder<>();
        binder.forField(design.userNameField).bind(Account::getNick, Account::setNick);
        binder.forField(design.passwordField).bind(Account::getPassword, Account::setPassword);
        binder.readBean(account);
        //todo implement event registration here
        design.loginButton.addClickListener(event -> {
            design.userNameField.setValue("Jacky");
            try {
                binder.writeBean(account);
            } catch (ValidationException e) {
                e.printStackTrace();
            }
            System.out.println(account.getNick());
        });
        setContent(design);
    }

}
