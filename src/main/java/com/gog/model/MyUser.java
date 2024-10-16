package com.gog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyUser {

    @Id
    private Integer id;
    private String name;
    private String department;
    private String salary;

    public MyFinalUser toFinalUser() {
        return new MyFinalUser(this);
    }

}
