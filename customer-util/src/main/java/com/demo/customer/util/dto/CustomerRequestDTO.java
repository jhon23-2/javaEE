package com.demo.customer.util.dto;

public class CustomerRequestDTO {
    private String name;
    private String lastName;

    public CustomerRequestDTO() {
    }

    public CustomerRequestDTO(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
