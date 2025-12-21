package com.example.demo.ui.view;

import com.example.demo.ui.layout.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {
    public HomeView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        VerticalLayout leftPanel = new VerticalLayout();
        leftPanel.setWidth("200px");
        leftPanel.getStyle().setBackgroundColor("black");

        VerticalLayout mainContent = new VerticalLayout();
        mainContent.setSizeFull();
        mainContent.add(new Span("Основной контент страницы"));

        Div eventCardDiv = new Div();

        HorizontalLayout body = new HorizontalLayout(leftPanel, mainContent);
        body.setSizeFull();
        body.expand(mainContent);
        body.setPadding(true);
        body.setSpacing(true);

        add(body);
    }
}
