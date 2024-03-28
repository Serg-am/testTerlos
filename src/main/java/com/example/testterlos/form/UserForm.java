/*
package com.example.testterlos.form;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.security.core.userdetails.User;

public class UserForm extends FormLayout {

    private TextField lastName = new TextField("Last Name");
    private TextField firstName = new TextField("First Name");
    private TextField middleName = new TextField("Middle Name");
    private DatePicker birthDate = new DatePicker("Birth Date");
    private EmailField email = new EmailField("Email");
    private TextField phoneNumber = new TextField("Phone Number");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Binder<User> binder = new Binder<>(User.class);

    public UserForm() {
        binder.bindInstanceFields(this);

        add(lastName, firstName, middleName, birthDate, email, phoneNumber, save, delete);
    }

    public void setUser(User user) {
        binder.setBean(user);

        if (user.getId() != null) {
            delete.setVisible(true);
        } else {
            delete.setVisible(false);
        }
    }

    // Другие методы для работы с формой
}
*/
