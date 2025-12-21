package com.example.demo.ui.view;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("/register")
public class RegisterView extends VerticalLayout {
    private final UserService userService;

    public RegisterView(UserService userService) {
        this.userService = userService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        TextField firstName = new TextField("Имя");
        TextField lastName = new TextField("Фамилия");
        TextField email = new TextField("Почта");
        TextField gender = new TextField("Пол");
        // todo dateOfBirth
        PasswordField password = new PasswordField("Пароль");
        PasswordField confirmPassword = new PasswordField("Подтвердите пароль");
        Button registerButton = new Button("Регистрация");

        add(
            firstName, lastName, email, gender, password, confirmPassword, registerButton
        );

        registerButton.addClickListener(e -> {
            String firstNameValue = firstName.getValue();
            String lastNameValue = lastName.getValue();
            String emailValue = email.getValue();
            String genderValue = gender.getValue();
            String passwordValue = password.getValue();
            String confirmPasswordValue = confirmPassword.getValue();

            if (firstNameValue.isEmpty() || lastNameValue.isEmpty() || emailValue.isEmpty()) {
                Notification.show("Имя или фамилия или почта не заполнена!");
                return;
            }

            if (!passwordValue.equals(confirmPasswordValue)){
                Notification.show("Пароли не совпадают!");
                return;
            }

            User savedUser = userService.customSave(firstNameValue, lastNameValue, emailValue, passwordValue);
            if (savedUser != null) {
                Notification.show("Регистрация прошла успешно!");
                UI.getCurrent().navigate("");
            } else {
                Notification.show("Ошибка при регистраций!");
            }
        });
    }
}
