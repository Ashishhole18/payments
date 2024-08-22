package com.payment.payments.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.payment.payments.employee.EmployeeResource;
import com.payment.payments.entity.Employee;
import com.payment.payments.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class CacheConfig {


    private final EmployeeRepository employeeRepository;

    private final LoadingCache<Integer, Employee> loadingCache;

    @Autowired
    public CacheConfig(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<Integer, Employee>() {
            @Override
            public Employee load(Integer key) throws Exception {
                System.out.println("Id" + key + " is being loaded from DB ......");
               return employeeRepository.getReferenceById(key);
            }
        });

    }

    public EmployeeResource getEmployee(final Integer key) throws ExecutionException {
        System.out.println("Id" + key + " is being loaded from Cache .............");

        Employee employee = this.loadingCache.get(key);

        return new EmployeeResource(employee.getId(), employee.getName(), employee.getAddress(), employee.getSalary(), employee.getDesignation());
    }


}
