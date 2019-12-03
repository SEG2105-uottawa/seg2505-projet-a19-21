package com.a19_21.clinicapp.test;

import com.a19_21.clinicapp.model.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    User user = new User("id", "mariam", "mariam@gmail.com", "123456", "Admin");

    @Test
    public void check_ID(){
        assertEquals("Check the user id", "id", user.getUserId());
        System.out.println("ID --> OK");
    }

    @Test
    public void check_username(){
        assertEquals("Check the username", "mariam", user.getUsername());
        System.out.println("ID --> OK");
    }

    @Test
    public void check_email(){
        assertEquals("Check the user email", "mariam@gmail.com", user.getEmail());
        System.out.println("ID --> OK");
    }

    @Test
    public void check_password(){
        assertEquals("Check the user password", "123456", user.getPassword());
        System.out.println("ID --> OK");
    }

    @Test
    public void check_type(){
        assertEquals("Check the user type", "Admin", user.getType());
        System.out.println("ID --> OK");
    }

}