package com.example.testterlos.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CompositeUserDetailsService implements UserDetailsService {

    private final List<UserDetailsService> delegates;

    public CompositeUserDetailsService(List<UserDetailsService> delegates) {
        this.delegates = delegates;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (UserDetailsService delegate : delegates) {
            try {
                UserDetails userDetails = delegate.loadUserByUsername(username);
                if (userDetails != null) {
                    return userDetails;
                }
            } catch (UsernameNotFoundException ignored) {
                // If the delegate cannot find the user, continue to the next delegate
            }
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}
