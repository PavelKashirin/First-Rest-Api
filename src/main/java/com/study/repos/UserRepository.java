package com.study.repos;

import com.study.model.MyUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<MyUser, Long> {

    MyUser findByFirstNameAndLastName(String firstName, String lastName); //поиск пользователя по имени и фамилии
}
