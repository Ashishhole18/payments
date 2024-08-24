package com.payment.payments.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.payment.payments.employee.EmployeeResource;
import com.payment.payments.employee.EmployeeResourceConverter;
import com.payment.payments.employee.EmployeeResponse;
import com.payment.payments.entity.Employee;
import com.payment.payments.kafka.publisher.employee.EmployeeMessage;
import com.payment.payments.kafka.publisher.employee.EmployeeMessagePublisher;
import com.payment.payments.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;

class EmployeeServiceTest {

    private  EmployeeRepository employeeRepository;
    private  EmployeeMessagePublisher employeeMessagePublisher;
    private  EmployeeResourceConverter employeeResourceConverter;
    private  String topicName="sometopic";
    private  ObjectMapper objectMapper;
    private LoadingCache<Integer, Employee> employeeIntegerLoadingCache;
    private EmployeeService employeeService;

    @BeforeEach
    void init() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeMessagePublisher = Mockito.mock(EmployeeMessagePublisher.class);
        employeeResourceConverter = Mockito.mock(EmployeeResourceConverter.class);

        objectMapper = Mockito.mock(ObjectMapper.class);

        employeeService = new EmployeeService(employeeRepository,employeeMessagePublisher, employeeResourceConverter, topicName, objectMapper);

    }
    @Test
    void createEmployee() {


        EmployeeResource employeeResource= Mockito.mock(EmployeeResource.class);
        Employee employee = Mockito.mock(Employee.class);



        Mockito.when(employeeResourceConverter.convertResourceToEmployee(employeeResource)).thenReturn(employee);
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        Mockito.when(employee.getAddress()).thenReturn("Pune");

        Mockito.when(employee.getSalary()).thenReturn(1321.00);
        Mockito.when(employee.getName()).thenReturn("Ashish");
        Mockito.when(employee.getDesignation()).thenReturn("SE");


        Mockito.doNothing().when(employeeMessagePublisher).publishMessage(topicName, Mockito.mock(EmployeeMessage.class));

        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeResource);
        InOrder inOrder = inOrder(employeeResourceConverter, employeeRepository);
        Mockito.verify(employeeResourceConverter).convertResourceToEmployee(employeeResource);

        inOrder.verify(employeeResourceConverter).convertResourceToEmployee(employeeResource);

        inOrder.verify(employeeRepository).save(employee);
    }

    @Test
    void saveAllEmployee() {
    }

    @Test
    void getAllEmployees() {
    }

    @Test
    void deleteEmployeeById() {
    }

    @Test
    void deleteEmployeeByIds() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteAllEmployees() {
    }
}