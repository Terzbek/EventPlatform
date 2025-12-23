package com.example.demo.ui.view;

import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import com.example.demo.ui.layout.MainLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.card.CardVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.card.Card;


import java.util.List;

@PageTitle("Home page")
@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {
    private final EventService eventService;

    public HomeView(EventService eventService) {
        this.eventService = eventService;
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        VerticalLayout leftPanel = new VerticalLayout();
        leftPanel.setWidth("250px");

        leftPanel.add(new Span("Фильтры"));
        leftPanel.setFlexGrow(0);
        TextField inputTitle = new TextField();
        inputTitle.setPlaceholder("Search by event title");
        leftPanel.add(inputTitle);

        Select<String> selectCountry = new Select<>();
        selectCountry.setLabel("Страна");
        selectCountry.setItems("Kazakhstan", "Russia", "USA", "UK", "China");
        selectCountry.setValue("Kazakhstan");
        leftPanel.add(selectCountry);

        HorizontalLayout bhl = new HorizontalLayout(new Button("Применить"), new Button("Сбросить"));
        leftPanel.add(bhl);

        VerticalLayout mainContent = new VerticalLayout();
        mainContent.setSizeFull();
        mainContent.add(new Span("Основной контент страницы"));



        List<Event> events = eventService.getEvents();
        for (Event event: events){
            Card eventCard = new Card();
            eventCard.addThemeVariants(
                    CardVariant.LUMO_OUTLINED,
                    CardVariant.LUMO_ELEVATED,
                    CardVariant.LUMO_HORIZONTAL
            );

            Image eventPoster = new Image("/images/poster_2.jpg", "second poster alt");
            eventPoster.setWidth("150px");
            eventPoster.setHeight("auto");
            eventPoster.getStyle().set("object-fit", "cover");
            eventCard.setMedia(eventPoster);
            eventCard.setTitle(new Div(event.getTitle()));
            eventCard.setSubtitle(new Paragraph(event.getCategory()));
            eventCard.add(new Paragraph(event.getDescription()));
            eventCard.add(new Paragraph(event.getCountry()));
            eventCard.setHeaderPrefix(new Avatar());
            Span badge = new Span("Завтра");
            badge.getElement().getThemeList().add("badge success");
            eventCard.setHeaderSuffix(badge);
            eventCard.setWidthFull();
            Button bookVacationButton = new Button("Добавить в избранное");
            Button learnMoreButton = new Button("Подробнее");
            eventCard.addToFooter(bookVacationButton, learnMoreButton);
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
