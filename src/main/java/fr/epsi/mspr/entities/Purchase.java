package fr.epsi.mspr.entities;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data @AllArgsConstructor @RequiredArgsConstructor
public class Purchase {

    private int id;
    private String reference;
    private LocalDateTime dateOfOrder;
    private String comment;

}
