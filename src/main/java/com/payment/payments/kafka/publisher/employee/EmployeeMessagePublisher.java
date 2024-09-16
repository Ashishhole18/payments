package com.payment.payments.kafka.publisher.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMessagePublisher implements KafkaProducer<EmployeeMessage>{

    private final KafkaTemplate<String, EmployeeMessage> kafkaTemplate;
    private final String employeePublisherTopic;

    @Autowired(required = false)
    public EmployeeMessagePublisher(KafkaTemplate<String, EmployeeMessage> kafkaTemplate,
                                    @Value("${student.publisher.topic}") String employeePublisherTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.employeePublisherTopic = employeePublisherTopic;
    }

    @Override
    public void publishMessage(String topicName, EmployeeMessage message) {
        System.out.println("Sending Kafka Messages ------->"+message);
        kafkaTemplate.send(employeePublisherTopic, message);
    }



}
