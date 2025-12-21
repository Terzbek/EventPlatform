package com.example.demo.ui.view;

import com.example.demo.ui.layout.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route(value = "events", layout = MainLayout.class)
public class EventsView extends VerticalLayout {
    public EventsView() {
        add(new H2("Список событий"));
    }
}
