package com.study.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Сущность описывающая структуру таблицы, поля это столбцы
 */
@Entity
public class MyUser {
    //id идентификатор генерируется автоматически(стратегия для генерации id локально в этой таблице)
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String firstName; //имя
    private String lastName; //фамилия

    public MyUser() {
    }

//    public long getId() {
//        return id;
//    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "MyUser{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUser myUser = (MyUser) o;
        return id == myUser.id && Objects.equals(firstName, myUser.firstName) && Objects.equals(lastName, myUser.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
