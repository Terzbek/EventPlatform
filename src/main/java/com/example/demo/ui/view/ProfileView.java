package com.example.demo.ui.view;


import com.example.demo.ui.layout.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Profile page")
@Route(value = "", layout = MainLayout.class)
public class ProfileView extends VerticalLayout {
    public ProfileView() {

    }
}
