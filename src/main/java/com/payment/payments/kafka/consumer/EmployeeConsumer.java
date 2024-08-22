package com.payment.payments.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.payments.employee.EmployeeResource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmployeeConsumer {

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(
            topics = "${student.publisher.topic}"
    )
    public void consumer(@Payload String message, @Headers Map<String, Object> header) throws JsonProcessingException {
        EmployeeResource employeeResource = objectMapper.readValue(message, EmployeeResource.class);

        System.out.println("Consuming employee Messages"+ employeeResource);
    }
}
