package com.study.controller;

import com.study.model.MyUser;
import com.study.repos.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MyControllerTest {

    UserRepository repos;

    @Autowired
    public UserRepository getRepos() {
        return repos;
    }

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getNewUser() throws Exception {

        mockMvc.perform(post("/user/new")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Paul\",\"lastName\":\"Logan\"}"))
                .andExpect(status().isOk());

        Assert.assertNotNull(repos.findByFirstNameAndLastName("Paul", "Logan"));
    }

    @Test
    public void getAllUser() throws Exception {
        String expected = "[{\"firstName\":\"Paul\",\"lastName\":\"Logan\"},{\"firstName\":\"Pol\",\"lastName\":\"Lign\"},{\"firstName\":\"Poly\",\"lastName\":\"Ligny\"}]";

        List<MyUser> list = (List<MyUser>) repos.findAll();
        Assert.assertTrue(list.size() > 0);

        mockMvc.perform(get("/user/all")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is(expected)));
    }

    @Test
    public void getUserByID() throws Exception {
        MyUser myUser = new MyUser();
        myUser.setId(2);
        myUser.setFirstName("Pol");
        myUser.setLastName("Lign");
        repos.save(myUser);

        mockMvc.perform(get("/user/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(is("{\"firstName\":\"Pol\",\"lastName\":\"Lign\"}")));
    }

    @Test
    public void updateUser() throws Exception {
        MyUser myUser = new MyUser();
        myUser.setId(3L);
        myUser.setFirstName("Pol");
        myUser.setLastName("Lign");
        repos.save(myUser);

        mockMvc.perform(put("/user/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Poly\",\"lastName\":\"Ligny\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUser() throws Exception {
        MyUser myUser = new MyUser();
        myUser.setId(4L);
        myUser.setFirstName("Four");
        myUser.setLastName("MyUser");
        repos.save(myUser);

        mockMvc.perform(delete("/user/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Four"))
                .andExpect(jsonPath("$.lastName").value("MyUser"));
    }
}