package com.payment.payments.employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponse {

    private Integer id;
    private String name;
    private String address;
    private Double salary;
    private String designation;
}
