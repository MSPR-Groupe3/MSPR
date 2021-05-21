package fr.epsi.mspr.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data @AllArgsConstructor @RequiredArgsConstructor
public class Organization {

    private int id;
    private String companyName;
    private String streetName1;
    private String streetName2;
    private String streetName3;
    private String cityName;
    private String postalCode;
    private String country;
    private String phoneNumber;
    private String email;

}
