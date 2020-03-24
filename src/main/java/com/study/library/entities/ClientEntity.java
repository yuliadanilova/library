package com.study.library.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class ClientEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Long passport;
    @Column
    private Long age;
    @OneToMany( mappedBy = "client" )
    private List<RentEntity> rents;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPassport() {
        return passport;
    }

    public void setPassport(Long passport) {
        this.passport = passport;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<RentEntity> getRents() {
        return rents;
    }

    public void setRents(List<RentEntity> rents) {
        this.rents = rents;
    }
}
