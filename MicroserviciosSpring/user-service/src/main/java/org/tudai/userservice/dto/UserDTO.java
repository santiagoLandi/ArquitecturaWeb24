package org.tudai.userservice.dto;

import lombok.Getter;
import org.tudai.userservice.entity.User;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDTO {
    private String userName;
    private String name;
    private String lastName;
    private Long phoneNumber;
    private String email;
    private List<Long> accountDTOList;

    public UserDTO(String userName, String name, String lastName, Long phoneNumber, String email) {
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accountDTOList = new ArrayList<>();
    }
    public UserDTO(String userName, String name, String lastName, Long phoneNumber, String email, List<Long> accountDTOList) {
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accountDTOList = accountDTOList;
    }

    public UserDTO(User user) {
        this.userName = user.getUsername();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.accountDTOList = new ArrayList<>();
    }

}
