package com.example.User.Service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTable {

    @Id
    @Column(name = "ID")
    private String userId;
    @Column(name = "NAME",length = 15)
    private String userName;
    private String email;
    private String about;


    transient private List<Rating> ratings=new ArrayList<>();
}
