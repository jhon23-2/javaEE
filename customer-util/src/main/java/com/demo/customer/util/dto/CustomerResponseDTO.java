package com.demo.customer.util.dto;

public class CustomerResponseDTO {
    private String id;
    private String name;
    private String lastName;

    public CustomerResponseDTO() {
    }

    public CustomerResponseDTO(String id, String lastName, String name) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
