package com.example.testterlos.view;


import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@AnonymousAllowed
public class LoginView extends LoginOverlay implements BeforeEnterObserver {

    public LoginView() {
        setAction("login");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }
}