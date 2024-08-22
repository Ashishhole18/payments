package com.payment.payments.kafka.publisher.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeMessage {
    private Integer id;
    private String name;

    private String address;
    private Double salary;
    private String designation;

}
