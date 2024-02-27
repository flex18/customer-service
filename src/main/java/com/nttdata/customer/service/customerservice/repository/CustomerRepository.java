package com.nttdata.customer.service.customerservice.repository;

import com.nttdata.customer.service.customerservice.entity.CustomerResponse;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<CustomerResponse, String> {
}
