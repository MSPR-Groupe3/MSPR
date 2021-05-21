package fr.epsi.mspr.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data @AllArgsConstructor @RequiredArgsConstructor
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String loginEmail;
    private String password;
    private String role;

}