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
public class MyFinalUser {

    @Id
    private Integer id;
    private String name;
    private String department;
    private String salary;

    public MyFinalUser(MyUser myUser) {
        this.id = myUser.getId();
        this.name = myUser.getName();
        this.department = myUser.getDepartment();
        this.salary = myUser.getSalary();
    }
}
