package com.payment.payments.rest.employee;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.payment.payments.employee.EmployeeResource;
import com.payment.payments.employee.EmployeeResponse;
import com.payment.payments.entity.Employee;
import com.payment.payments.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    final private EmployeeService employeeService;


    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    @PostMapping(value = "/create-employee")
    public ResponseEntity createEmployee(@RequestBody EmployeeResource employeeResource) {
        EmployeeResponse employeeResponse = this.employeeService.createEmployee(employeeResource);
        String str= new String("KI");
        StringBuffer buffer = new StringBuffer("Hi");
        return ResponseEntity.ok(employeeResponse);
    }

    @PostMapping(value = "/save-multiple-employee")
    public ResponseEntity saveMultipleEntity(@RequestBody List<EmployeeResource> employeeResources) throws IOException {
        List<EmployeeResponse> employeeResponses = this.employeeService.saveAllEmployee(employeeResources);
        return ResponseEntity.ok(employeeResponses);
    }

    @GetMapping(value = "/get-all-employee")
    public ResponseEntity getAllEmployee() throws IOException {
        return ResponseEntity.ok(this.employeeService.getAllEmployees());
    }

    @DeleteMapping(value = "/delete-emp-id/{empId}")
    public ResponseEntity deleteEmployeeById(@PathVariable Integer empId) {
        this.employeeService.deleteEmployeeById(empId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("employee with emp id "+ empId +" has been deleted");
    }

    @PostMapping(value = "/delete-employess-ids")
    public ResponseEntity deleteEmployeesByIds(@RequestBody List<Integer> employeeIds) {
        this.employeeService.deleteEmployeeByIds(employeeIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("All employees has been deleted" + employeeIds);
    }

    @PutMapping(value = "update-employee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeResource employeeResource) {
        return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.updateEmployee(employeeResource));
    }

    @DeleteMapping(value = "/delete-all-employee")
    public ResponseEntity deleteAllEmployee() {
        this.employeeService.deleteAllEmployees();
        return ResponseEntity.ok("Deleted All Employees");
    }

}
