package com.example.demo.ui.layout;

import com.example.demo.ui.view.EventsView;
import com.example.demo.ui.view.HomeView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    public MainLayout() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setHeight("50px");
        header.setMargin(false);
        header.setPadding(false);
        header.getStyle().set("padding", "0 16px");
        header.getStyle().set("border", "1px solid black");
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        Image logo = new Image("/images/logo.png", "Event Platform logo");
        logo.setHeight("50px");
        logo.setWidth("auto");
        header.add(logo);

        HorizontalLayout headerRightContent = new HorizontalLayout();
        headerRightContent.setAlignItems(FlexComponent.Alignment.CENTER);
        Icon notificationIcon = VaadinIcon.BELL_O.create();
        Avatar avatarBasic = new Avatar();
        ContextMenu avatarMenu = new ContextMenu(avatarBasic);
        avatarMenu.setOpenOnClick(true);
        avatarMenu.addItem("Profile", e ->{
            System.out.println("Profile clicked!");
        });

        headerRightContent.add(notificationIcon, avatarBasic);

        header.add(headerRightContent);
        addToNavbar(header);

    }
}