package com.example.demo.ui.view;

import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import com.example.demo.ui.layout.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

import java.util.List;


@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {
    private final EventService eventService;

    public HomeView(EventService eventService) {
        this.eventService = eventService;

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        VerticalLayout leftPanel = new VerticalLayout();
        leftPanel.setWidth("200px");
        leftPanel.getStyle().setBackgroundColor("black");

        VerticalLayout mainContent = new VerticalLayout();
        mainContent.setSizeFull();
        mainContent.add(new Span("Основной контент страницы"));


        List<Event> events = eventService.getEvents();

        for (Event event: events){
            HorizontalLayout eventCard = new HorizontalLayout();
            eventCard.setHeight("150px");
            eventCard.setWidthFull();
            eventCard.setAlignItems(Alignment.CENTER); // 🔑 вертикальное выравнивание
            eventCard.setPadding(true);
            eventCard.setSpacing(true);
            eventCard.addClassName("event-card");

            Image eventCardPoster = new Image("/images/poster.png", "poster logo");
            eventCardPoster.setHeight("100px");
            eventCardPoster.setWidth("70px");
            eventCardPoster.getStyle().set("object-fit", "cover");

            VerticalLayout eventCardBody = new VerticalLayout();
            eventCardBody.setHeightFull();
            eventCardBody.setWidthFull();
            eventCardBody.setPadding(false);
            eventCardBody.setSpacing(false);
            eventCardBody.setJustifyContentMode(JustifyContentMode.CENTER);

            H2 eventTitle = new H2(event.getTitle());
            eventTitle.getStyle()
                    .set("margin", "0")
                    .set("font-size", "16px")
                    .set("white-space", "nowrap")
                    .set("overflow", "hidden")
                    .set("text-overflow", "ellipsis");

            Span eventDescription = new Span();
            eventDescription.setText(event.getDescription());
            eventDescription.getStyle()
                    .set("font-size", "13px")
                    .set("color", "gray")
                    .set("overflow", "hidden")
                    .set("display", "-webkit-box")
                    .set("-webkit-line-clamp", "2")
                    .set("-webkit-box-orient", "vertical");

            Span eventAddress = new Span(event.getCountry());
            eventAddress.getStyle()
                    .set("font-size", "12px")
                    .set("color", "#666");

            Span eventTime = new Span(
                    event.getStartDateTime() + " – " + event.getEndDateTime()
            );
            eventTime.getStyle()
                    .set("font-size", "12px")
                    .set("color", "#999");

            eventCardBody.add(eventTitle, eventDescription, eventAddress, eventTime);
            eventCard.add(eventCardPoster, eventCardBody);
            mainContent.add(eventCard);
        }

        HorizontalLayout body = new HorizontalLayout(leftPanel, mainContent);
        body.setSizeFull();
        body.expand(mainContent);
        body.setPadding(true);
        body.setSpacing(true);

        add(body);
    }
}
