package com.nttdata.customer.service.customerservice.repository;

import com.nttdata.customer.service.customerservice.entity.enterprise.EnterpriseCustomer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EnterpriseCustomerRepository extends ReactiveMongoRepository<EnterpriseCustomer, String> {
}
