package com.study.controller;

import com.study.model.*;
import com.study.repos.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * контроллер
 */
@Controller
public class MyController {

    private UserRepository repository; //объект для выполнения запросов в базу данных, формируется автоматически

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * метод описывающий действия программы на GET запрос на end point: "/user/all"
     * @return список всех пользователей в базе данных
     */
    @GetMapping(value = "/user/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<MyUser> getAllUser() {
        List<MyUser> result = new ArrayList<>();
        for (MyUser myUser : repository.findAll()) {
            result.add(myUser);
        }
        return result;
    }

    /**
     * метод описывающий действия программы на POST запрос на end point: "/myUser/new"
     * @param myUser объект получаемый из тела запроса
     * @return объект добавленный в базу данных
     */
    @PostMapping(value = "/user/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    MyUser getNewUser(@RequestBody MyUser myUser) {
        repository.save(myUser);

        return repository.findByFirstNameAndLastName(myUser.getFirstName(), myUser.getLastName());
    }

    /**
     * метод описывающий действия программы на GET запрос на end point: "/user/{id}"
     * @param id пользователя для получения информации
     * @return пользователь из базы данных в формате JSON
     */
    @GetMapping(value = "/user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    MyUser getUserByID(@PathVariable Long id) {
        // получение и отправка объекта пользователя по полученному значению id,
        // если пользователь не найден вернётся пользователь с пустыми полями и id = 0
        return repository.findById(id).orElse(new MyUser());
    }

    /**
     * метод описывающий действия программы на PUT запрос на end point: "/user/{id}"
     * @param id пользователя для изменения данных
     * @return пользователь из базы данных в формате JSON
     */
    @PutMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    MyUser updateUser(@PathVariable Long id, @RequestBody MyUser myUserIn) {
        // получение объекта пользователя для изменения,
        // если пользователь не найден вернётся пользователь с пустыми полями и id = 0
        MyUser result = repository.findById(id).orElse(new MyUser());
        result.setFirstName(myUserIn.getFirstName());
        result.setLastName(myUserIn.getLastName());
        repository.save(result);

        return result;
    }

    /**
     * метод описывающий действия программы на DELETE запрос на end point: "/user/{id}"
     * @param id пользователя для удаления
     * @return удалённого пользователя из базы данных в формате JSON
     */
    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    MyUser deleteUser(@PathVariable Long id) {
        // получение объекта пользователя для удаления,
        // если пользователь не найден вернётся пользователь с пустыми полями и id = 0
        MyUser myUserWasDelete = repository.findById(id).orElse(new MyUser());
        repository.deleteById(id); // удаление пользователя

        return myUserWasDelete;
    }
}
