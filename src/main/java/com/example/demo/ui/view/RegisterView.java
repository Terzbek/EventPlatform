package com.example.demo.ui.view;


import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@Route("/register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {
    private final Binder<UserDto> binder = new Binder<>(UserDto.class);

    public RegisterView(UserService userService) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H2 title = new H2("Регистрация");
        TextField taxCode = new TextField("ИИН");
        taxCode.setRequired(true);
        TextField firstName = new TextField("Имя");
        firstName.setRequired(true);
        TextField lastName = new TextField("Фамилия");
        lastName.setRequired(true);
        EmailField email = new EmailField("Почта");
        email.setRequired(true);
        PasswordField password = new PasswordField("Пароль");
        password.setRequired(true);
        PasswordField confirmPassword = new PasswordField("Подтвердите пароль");
        confirmPassword.setRequired(true);

        binder.forField(taxCode)
                .asRequired("Введите ИИН")
                .bind(UserDto::getTaxCode, UserDto::setTaxCode);
        binder.forField(firstName)
                .asRequired("Введите имя")
                .bind(UserDto::getFirstName, UserDto::setFirstName);
        binder.forField(lastName)
                .asRequired("Введите фамилию")
                .bind(UserDto::getLastName, UserDto::setLastName);
        binder.forField(email)
                .asRequired("Введите email")
                .withValidator(
                        value -> value.contains("@"),
                        "Некорректный email"
                )
                .bind(UserDto::getEmail, UserDto::setEmail);
        binder.forField(password)
                .asRequired("Введите пароль")
                .withValidator(
                        value -> value.length() >= 6,
                        "Пароль должен быть не менее 6 символов"
                )
                .bind(UserDto::getPassword, UserDto::setPassword);

        binder.forField(confirmPassword)
                .asRequired("Повторите пароль")
                .withValidator(
                        value -> value.equals(password.getValue()),
                        "Пароли не совпадают"
                )
                .bind(UserDto::getConfirmPassword, UserDto::setConfirmPassword);

        Span error = new Span();
        error.getStyle().setColor("red");


        FormLayout formLayout = new FormLayout(taxCode, firstName, lastName, email, password, confirmPassword);
        formLayout.setWidthFull();
        formLayout.getElement().getStyle().set("max-width", "400px");
        formLayout.getElement().getStyle().set("margin-left", "auto");
        formLayout.getElement().getStyle().set("margin-right", "auto");

        Button registerButton = new Button("Зарегистрироваться");
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancelButton = new Button("Отмена");

        registerButton.addClickListener(e -> {
            UserDto registrationForm = new UserDto();
            try {
                binder.writeBean(registrationForm);
                userService.register(registrationForm);

                error.setText("Регистрация прошла успешно!");
                error.getStyle().set("color", "green");
            } catch (ValidationException ex) {
                error.setText("Исправьте ошибки в форме");
            }
        });
        HorizontalLayout buttons = new HorizontalLayout(registerButton, cancelButton);
        add(title, formLayout, buttons, error);
    }
}
