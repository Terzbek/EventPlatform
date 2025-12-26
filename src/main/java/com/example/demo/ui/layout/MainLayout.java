package com.example.demo.ui.layout;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.popover.Popover;
import com.vaadin.flow.component.popover.PopoverPosition;
import com.vaadin.flow.component.popover.PopoverVariant;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.theme.lumo.LumoIcon;


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

        Button notificationIcon = new Button(LumoIcon.BELL.create());
        notificationIcon.addThemeVariants(ButtonVariant.LUMO_ICON);
        notificationIcon.setAriaLabel("Notifications");

        Popover notificationOverlay = new Popover();
        notificationOverlay.setTarget(notificationIcon);
        notificationOverlay.addThemeVariants(PopoverVariant.ARROW);
        notificationOverlay.setPosition(PopoverPosition.BOTTOM_END);
        notificationOverlay.setWidth("300px");
        notificationOverlay.setHeight("400px");

        H4 text = new H4("Уведомления");
        text.setId("notifications-heading");
        text.getStyle().set("margin", "0");
        Button markRead = new Button("Mark all read", (e) -> {
            System.out.println("Mark all read button clicked!");
        });
        markRead.getStyle().set("margin", "0 0 0 auto");
        markRead.addThemeVariants(ButtonVariant.LUMO_SMALL);

        HorizontalLayout notificationOverlayHead = new HorizontalLayout(text, markRead);
        notificationOverlayHead.setWidthFull();

        Div unreadNotifContent = new Div("Hello");
        TabSheet notifTabSheet = new TabSheet();
        notifTabSheet.add("Непрочитанные", unreadNotifContent);
        notifTabSheet.add("Все", new H4("Hello"));
        // Add style variants for Lumo theme
        notifTabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_SMALL,
                TabSheetVariant.LUMO_NO_PADDING);

        notificationOverlay.add(notificationOverlayHead, notifTabSheet);

        Avatar avatarBasic = new Avatar();
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