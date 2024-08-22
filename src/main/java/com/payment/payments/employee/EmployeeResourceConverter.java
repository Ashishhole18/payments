package com.payment.payments.employee;

import com.payment.payments.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeResourceConverter {

    public Employee convertResourceToEmployee(final EmployeeResource employeeResource) {
        return Employee.builder()
                .designation(employeeResource.getDesignation())
                .address(employeeResource.getAddress())
                .name(employeeResource.getName())
                .salary(employeeResource.getSalary())
                .build();
    }

    public List<Employee> convertResourcesToEmployees(List<EmployeeResource> employeeResources) {
        return employeeResources.stream().map(employeeResource -> convertResourceToEmployee(employeeResource)).collect(Collectors.toList());
    }
}
