package fr.epsi.mspr.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data @AllArgsConstructor @RequiredArgsConstructor
public class Contact {

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

}
