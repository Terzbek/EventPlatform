package com.example.demo.ui.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@Route("/auth")
@AnonymousAllowed
public class AuthView extends VerticalLayout {
    public AuthView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);


        // Settings of Form
        LoginI18n loginI18n = LoginI18n.createDefault();
        loginI18n.getForm().setTitle("Добро пожаловать!");
        loginI18n.getForm().setUsername("Пользователь");
        loginI18n.getForm().setPassword("Пароль");
        loginI18n.getForm().setSubmit("Войти");
        loginI18n.getErrorMessage().setTitle("Ошибка!");
        loginI18n.getErrorMessage().setMessage("Неверное имя пользователя или пароль!");



        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(loginI18n);
        loginForm.setAction("login");

        add(
                new H1("Event Platform"),
                loginForm
        );
    }
}
