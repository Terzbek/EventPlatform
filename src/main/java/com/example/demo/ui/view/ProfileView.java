package com.example.demo.ui.view;


import com.example.demo.ui.layout.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Profile page")
@Route(value = "profile", layout = MainLayout.class)
public class ProfileView extends HorizontalLayout {

    private final Div content = new Div();

    public ProfileView() {
        setSizeFull();

        Tab profileTab = new Tab(VaadinIcon.USER.create(), new Span("Profile"));
        Tab settingsTab = new Tab(VaadinIcon.COG.create(), new Span("Settings"));
        Tab notificationsTab = new Tab(VaadinIcon.BELL.create(), new Span("Notifications"));

        Tabs tabs = new Tabs(profileTab, settingsTab, notificationsTab);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setWidth("250px");

        content.setSizeFull();
        content.getStyle().set("padding", "20px");

        showProfile();

        tabs.addSelectedChangeListener(event -> {
            content.removeAll();

            if (event.getSelectedTab() == profileTab) {
                showProfile();
            } else if (event.getSelectedTab() == settingsTab) {
                showSettings();
            } else if (event.getSelectedTab() == notificationsTab) {
                showNotifications();
            }
        });

        add(tabs, content);
        expand(content);
    }

    private void showProfile() {
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSpacing(true);
        hl.setJustifyContentMode(JustifyContentMode.START);
        hl.setWidthFull();
        hl.setPadding(false);

        // ===== Аватар =====
        Image avatar = new Image("/images/me.jpg", "avatar");
        avatar.setWidth("150px");
        avatar.setHeight("150px");
        avatar.getStyle().set("border-radius", "50%");
        avatar.getStyle().set("object-fit", "cover");
        avatar.getStyle().set("flex-shrink", "0");

        VerticalLayout vl = new VerticalLayout();
        vl.setSpacing(true);
        vl.setPadding(false);
        vl.setAlignItems(Alignment.START);

        vl.add(createInfoRow("ФИО:", "Адильжан Адильбек"));
        vl.add(createInfoRow("Дата рождения:", "01.07.2003"));
        vl.add(createInfoRow("ИИН:", "030701501433"));
        vl.add(createInfoRow("Email:", "adilbek003@mail.ru"));
        vl.add(createInfoRow("Пол:", "М"));

        Div card = new Div();
        card.getStyle()
                .set("padding", "24px")
                .set("border-radius", "12px")
                .set("box-shadow", "0 2px 8px rgba(0,0,0,0.1)")
                .set("background-color", "var(--lumo-base-color)");
        card.add(hl);

        hl.add(avatar, vl);

        content.add(card);
    }

    private HorizontalLayout createInfoRow(String label, String value) {
        Span lbl = new Span(label);
        lbl.getStyle().set("font-weight", "bold");
        Span val = new Span(value);
        HorizontalLayout row = new HorizontalLayout(lbl, val);
        row.setSpacing(true);
        row.setAlignItems(Alignment.CENTER);
        return row;
    }

    private void showSettings() {
        content.add(
                new H2("Settings"),
                new Span("Настройки профиля")
        );
    }

    private void showNotifications() {
        content.add(
                new H2("Notifications"),
                new Span("Уведомления")
        );
    }
}
