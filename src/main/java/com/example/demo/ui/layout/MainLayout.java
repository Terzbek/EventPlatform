package com.example.demo.ui.layout;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.popover.Popover;
import com.vaadin.flow.component.popover.PopoverPosition;
import com.vaadin.flow.component.popover.PopoverVariant;


public class MainLayout extends AppLayout {
    public MainLayout() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setHeight("80px");
        header.setMargin(false);
        header.setPadding(false);
        header.getStyle().set("padding", "0 16px");
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        Image logo = new Image("/images/logo.png", "Event Platform logo");
        logo.setHeight("70px");
        logo.setWidth("auto");
        header.add(logo);

        HorizontalLayout headerRightContent = new HorizontalLayout();
        headerRightContent.setAlignItems(FlexComponent.Alignment.CENTER);
        Icon notificationIcon = VaadinIcon.BELL_O.create();

        Avatar avatarBasic = new Avatar();
        Popover avatarOverlay = new Popover();
        avatarOverlay.addThemeVariants(PopoverVariant.ARROW);
        avatarOverlay.setTarget(avatarBasic);
        avatarOverlay.setPosition(PopoverPosition.BOTTOM_END);
        VerticalLayout avatarOverlayContent = new VerticalLayout(
                createButtonFullWidth("Профиль"),
                createButtonFullWidth("Друзья"),
                createButtonFullWidth("Настройки"),
                createButtonFullWidth("Подписки"),
                createButtonFullWidth("Список желаемых")
        );
        avatarOverlayContent.setPadding(false);
        avatarOverlayContent.setSpacing(false);
        avatarOverlay.add(avatarOverlayContent);
        headerRightContent.getStyle().setMarginRight("50px");
        headerRightContent.add(notificationIcon, avatarBasic);

        header.add(headerRightContent);
        addToNavbar(header);

    }

    private Button createButtonFullWidth(String buttonTextContent){
        Button button = new Button(buttonTextContent);
        button.setWidthFull();

        return button;
    }
}