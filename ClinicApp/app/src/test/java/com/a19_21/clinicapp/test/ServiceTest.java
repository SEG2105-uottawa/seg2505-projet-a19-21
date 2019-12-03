package com.a19_21.clinicapp.test;

import com.a19_21.clinicapp.model.Service;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    Service service = new Service("id", "Chimiothérapie", "Pour le cancer principalement", 250.5);

    @Test
    public void check_ID(){
        assertEquals("Check the service id", "id", service.getServiceId());
        System.out.println("ID --> OK");
    }

    @Test
    public void check_name(){
        assertEquals("Check the service name", "Chimiothérapie", service.getName());
        System.out.println("Name --> OK");
    }

    @Test
    public void check_description(){
        assertEquals("Check the service description", "Pour le cancer principalement", service.getDescription());
        System.out.println("Description --> OK");
    }

    @Test
    public void check_fee(){
        assertEquals("Check the service fees", 250.5, service.getFee());
        System.out.println("Fee --> OK");
    }

}