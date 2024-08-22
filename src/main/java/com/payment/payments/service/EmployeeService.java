package com.payment.payments.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.payment.payments.employee.EmployeeResource;
import com.payment.payments.employee.EmployeeResourceConverter;
import com.payment.payments.employee.EmployeeResponse;
import com.payment.payments.entity.Employee;
import com.payment.payments.kafka.publisher.employee.EmployeeMessage;
import com.payment.payments.kafka.publisher.employee.EmployeeMessagePublisher;
import com.payment.payments.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMessagePublisher employeeMessagePublisher;

    private final EmployeeResourceConverter employeeResourceConverter;
    private final String topicName;
    private final ObjectMapper objectMapper;
    private LoadingCache<Integer, Employee> employeeIntegerLoadingCache;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMessagePublisher employeeMessagePublisher, EmployeeResourceConverter employeeResourceConverter,
                           @Value("${student.publisher.topic}") String topicName, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMessagePublisher = employeeMessagePublisher;
        this.employeeResourceConverter = employeeResourceConverter;
        this.topicName = topicName;
        this.objectMapper = objectMapper;
        instantiateCache();
        getAllIntoCache();
    }

    public EmployeeResponse createEmployee(final EmployeeResource employeeResource) {
        Employee employee = this.employeeResourceConverter.convertResourceToEmployee(employeeResource);
        Employee createdEmployee = this.employeeRepository.save(employee);
        EmployeeMessage employeeMessage = EmployeeMessage.builder()
                .id(createdEmployee.getId())
                .address(createdEmployee.getAddress())
                .salary(createdEmployee.getSalary())
                .name(createdEmployee.getName())
                .designation(createdEmployee.getDesignation())
                .build();
        this.employeeMessagePublisher.publishMessage(topicName, employeeMessage);
        return EmployeeResponse.builder()
                .name(createdEmployee.getName())
                .address(createdEmployee.getAddress())
                .designation(createdEmployee.getDesignation())
                .salary(createdEmployee.getSalary())
                .build();
    }

    public List<EmployeeResponse> saveAllEmployee(List<EmployeeResource> employeeResources) throws IOException {
        List<Employee> list = this.employeeResourceConverter.convertResourcesToEmployees(employeeResources);
        List<Employee> savedEmployees = this.employeeRepository.saveAll(list);
        return savedEmployees.stream().map(emp -> covertToEmployeesResponses(emp)).collect(Collectors.toList());
    }

    public List<EmployeeResponse> getAllEmployees() throws IOException {
        List<Employee> employees = this.employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = employees.stream().map(emp -> covertToEmployeesResponses(emp)).collect(Collectors.toList());
        return employeeResponses;

    }

    private EmployeeResponse covertToEmployeesResponses(Employee emp) {
        return EmployeeResponse.builder()
                .id(emp.getId())
                .salary(emp.getSalary())
                .name(emp.getName())
                .address(emp.getAddress())
                .designation(emp.getDesignation())
                .build();
    }

    public void deleteEmployeeById(Integer empId) {
        this.employeeRepository.deleteById(empId);
    }

    public void deleteEmployeeByIds(List<Integer> employeeIds) {
        this.employeeRepository.deleteAllById(employeeIds);
    }

    public EmployeeResponse updateEmployee(EmployeeResource employeeResource) {
        Employee employee = this.employeeResourceConverter.convertResourceToEmployee(employeeResource);
        Employee savedEmployee = this.employeeRepository.save(employee);
        return this.covertToEmployeesResponses(savedEmployee);
    }

    public void deleteAllEmployees() {
        this.employeeRepository.deleteAll();
    }

    private void instantiateCache() {
        employeeIntegerLoadingCache = CacheBuilder.newBuilder().build(new CacheLoader<Integer, Employee>() {
            @Override
            public Employee load(Integer key) throws Exception {
                return employeeRepository.getReferenceById(key);
            }
        });
    }

    private void getAllIntoCache() {
        Map<Integer, Employee> employeeMap =employeeRepository.findAll().stream().collect(Collectors.toMap(Employee::getId, employee -> employee));
        employeeIntegerLoadingCache.putAll(employeeMap);
    }
}
