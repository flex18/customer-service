package com.nttdata.customer.service.customerservice.service.inter;

import com.nttdata.customer.service.customerservice.entity.CustomerRequest;
import com.nttdata.customer.service.customerservice.entity.CustomerResponse;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerInterface {

  Mono<CustomerResponse> create(CustomerRequest request);

  Flux<CustomerResponse> listAll();

  Mono<CustomerResponse> getById(String id);

  Mono<CustomerResponse> update(String id, CustomerRequest request);

  Mono<Void> delete(String id);
}
