package com.a19_21.clinicapp.test;

import com.a19_21.clinicapp.controller.MainActivity;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    String password = "test";


    @Test
    public void check_passwordValid(){
        assertEquals("Check if password is valid", false, com.a19_21.clinicapp.controller.MainActivity.isPasswordValid(password));
        System.out.println("Password Valid --> OK");
    }

}