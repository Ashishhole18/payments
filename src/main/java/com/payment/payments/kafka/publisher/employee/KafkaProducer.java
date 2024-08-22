package com.payment.payments.kafka.publisher.employee;

public interface KafkaProducer<T> {
    public  void publishMessage(String topicName, T message);
}
