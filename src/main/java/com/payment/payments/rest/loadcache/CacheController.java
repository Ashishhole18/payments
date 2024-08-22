package com.payment.payments.rest.loadcache;

import com.payment.payments.cache.CacheConfig;
import com.payment.payments.employee.EmployeeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/loadcache")
public class CacheController {

    @Autowired
    CacheConfig cacheConfig;

    @GetMapping(value = "/{empId}")
    public ResponseEntity getEmployee(@PathVariable Integer empId) throws ExecutionException {
        return ResponseEntity.ok().body(cacheConfig.getEmployee(empId));
    }
}
