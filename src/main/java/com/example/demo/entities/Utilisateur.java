package com.example.demo.entities;

import com.example.demo.enumerations.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Data@AllArgsConstructor
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "utilisateur")
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String LastName;
    private String username;
    private String email;
    private String password;
    private String picture;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phone;
    private String address;
    private boolean state;

}
