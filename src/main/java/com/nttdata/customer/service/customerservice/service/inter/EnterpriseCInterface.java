package com.nttdata.customer.service.customerservice.service.inter;


import com.nttdata.customer.service.customerservice.entity.enterprise.EnterpriseCustomer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EnterpriseCInterface {

  Mono<EnterpriseCustomer> create(EnterpriseCustomer request);
  Flux<EnterpriseCustomer> listAll();
  Mono<EnterpriseCustomer> getById(String id);
  Mono<EnterpriseCustomer> update(String id, EnterpriseCustomer request);
  Mono<Void> delete(String id);
}
