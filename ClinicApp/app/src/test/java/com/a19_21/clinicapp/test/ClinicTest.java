package com.a19_21.clinicapp.test;

import com.a19_21.clinicapp.model.Clinic;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClinicTest {

    Clinic clinic = new Clinic("id", "Clinic test", "employee00");

    @Test
    public void check_ID(){
        assertEquals("Check the Clinic id", "id", clinic.getClinicId());
        System.out.println("ID --> OK");
    }

    @Test
    public void check_name(){
        assertEquals("Check the Clinic name", "Clinic test", clinic.getClinicName());
        System.out.println("Name --> OK");
    }

    @Test
    public void check_employeeID(){
        assertEquals("Check the Clinic employee", "employee00", clinic.getEmployeeID());
        System.out.println("EmployeeID --> OK");
    }

}