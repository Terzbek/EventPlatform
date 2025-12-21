package com.example.demo.ui.layout;

import com.example.demo.ui.view.EventsView;
import com.example.demo.ui.view.HomeView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    VerticalLayout pageContent = new VerticalLayout();

    public MainLayout() {
        pageContent.setSizeFull();
        pageContent.setPadding(false);
        pageContent.setSpacing(false);
        pageContent.setMargin(false);
        createHeader();
        setContent(pageContent);
    }

    private void createHeader() {
        Image logo = new Image("/images/logo.png", "Event Platform logo");
        logo.setWidth("50px");

        Div spacer = new Div();
        spacer.getStyle().set("flex-grow", "1");

        RouterLink homeLink = new RouterLink("Главная", HomeView.class);
        RouterLink eventsLink = new RouterLink("События", EventsView.class);
        HorizontalLayout menu = new HorizontalLayout(homeLink, eventsLink);

        HorizontalLayout header = new HorizontalLayout(
                logo,
                spacer,
                menu
        );
        header.setWidthFull();
        header.setPadding(true);
        header.setAlignItems(FlexComponent.Alignment.CENTER);

        addToNavbar(header);
    }

}
