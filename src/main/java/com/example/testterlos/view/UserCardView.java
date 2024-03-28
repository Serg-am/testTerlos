package com.example.testterlos.view;


import com.example.testterlos.entity.UserEntity;
import com.example.testterlos.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Base64;
import java.util.Optional;

@Route("user-card")
@PageTitle("User Card")
@AnonymousAllowed
public class UserCardView extends VerticalLayout {
    private final UserService userService;

    public UserCardView(UserService userService) {
        this.userService = userService;
        configureView();
    }

    private void configureView() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            Optional<UserEntity> userOptional = userService.getUserByEmail(email);
            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                addUserDetails(user);
            } else {
                add(new Text("User not found."));
            }
        } else {
            add(new Text("You must be logged in to view your user card."));
        }
    }
    private void addUserDetails(UserEntity user) {
        Text lastNameText = new Text("Last Name: " + user.getLastName());
        Text firstNameText = new Text("First Name: " + user.getFirstName());
        Text middleNameText = new Text("Middle Name: " + user.getMiddleName());
        Text birthDateText = new Text("Birth Date: " + user.getBirthDate());
        Text emailText = new Text("Email: " + user.getEmail());
        Text phoneNumberText = new Text("Phone Number: " + user.getPhoneNumber());

        Image photo = new Image();
        if (user.getPhoto() != null) {
            photo.getElement().setAttribute("src", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(user.getPhoto()));
        } else {
            photo.setSrc("default-profile-picture.png");
        }

        add(lastNameText, firstNameText, middleNameText, birthDateText, emailText, phoneNumberText, photo);
    }
}