package com.example.testterlos.view;


import com.example.testterlos.entity.UserEntity;
import com.example.testterlos.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.io.InputStream;
import java.time.LocalDate;

@Route("create-user")
@PageTitle("Create User")
@AnonymousAllowed
public class CreateUserView extends VerticalLayout {
    private final UserService userService;

    public CreateUserView(UserService userService) {
        this.userService = userService;
        configureView();
    }

    private void configureView() {
        TextField lastNameField = new TextField("Last Name");
        TextField firstNameField = new TextField("First Name");
        TextField middleNameField = new TextField("Middle Name");
        DatePicker birthDateField = new DatePicker("Birth Date");
        TextField emailField = new TextField("Email");
        TextField phoneNumberField = new TextField("Phone Number");
        MemoryBuffer photoBuffer = new MemoryBuffer();
        Upload photoUpload = new Upload(photoBuffer);
        photoUpload.setAcceptedFileTypes(".jpg", ".jpeg", ".png");
        Button createButton = new Button("Create User");

        // Добавляем обработчик события успешной загрузки файла
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("image/jpeg", "image/png");
        upload.addSucceededListener(event -> {
            String mimeType = event.getMIMEType();
            String fileName = event.getFileName();
            InputStream stream = buffer.getInputStream();
            Image image = new Image(String.valueOf(stream), fileName);
        });

        HorizontalLayout fields = new HorizontalLayout(lastNameField, firstNameField, middleNameField, birthDateField,
                emailField, phoneNumberField, photoUpload, createButton);
        fields.setAlignItems(Alignment.BASELINE);

        add(fields);
    }

    private void createUser(String lastName, String firstName, String middleName, LocalDate birthDate, String email,
                            String phoneNumber, byte[] photo) {
        UserEntity user = new UserEntity();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setBirthDate(birthDate);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPhoto(photo);

        try {
            userService.saveUser(user);
            Notification.show("User created successfully");
        } catch (Exception ex) {
            Notification.show("An error occurred while creating the user");
        }
    }
}
