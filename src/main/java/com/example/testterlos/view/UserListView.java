package com.example.testterlos.view;


import com.example.testterlos.entity.UserEntity;
import com.example.testterlos.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("users")
@PageTitle("User List")
@AnonymousAllowed
public class UserListView extends VerticalLayout {
    private final UserService userService;
    private Grid<UserEntity> grid;

    public UserListView(UserService userService) {
        this.userService = userService;
        configureGrid();
        add(grid);
    }

    private void configureGrid() {
        grid = new Grid<>(UserEntity.class);
        grid.setColumns("id", "lastName", "firstName", "middleName", "birthDate", "email", "phoneNumber");
        grid.setItems(userService.getAllUsers());
    }
}

