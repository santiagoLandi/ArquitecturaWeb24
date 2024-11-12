package org.tudai.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String lastName;
    private Long phoneNumber;
    private String email;
    @ElementCollection
    private List<Long> accountsId;

    public User() {}

    public User(String username, String name, String lastName, Long phoneNumber, String email) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accountsId = new ArrayList<>();
    }


}
