package com.payment.payments.employee;

import lombok.ToString;

@ToString
public class EmployeeResource {

    private Integer id;
    private String name;

    private String address;
    private Double salary;
    private String designation;

    public EmployeeResource() {
    }

    public EmployeeResource(Integer id, String name, String address, Double salary, String designation) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.designation = designation;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Double getSalary() {
        return salary;
    }

    public String getDesignation() {
        return designation;
    }
}
