package com.nttdata.customer.service.customerservice.service.impl;

import com.nttdata.customer.service.customerservice.entity.enterprise.EnterpriseCustomer;
import com.nttdata.customer.service.customerservice.repository.EnterpriseCustomerRepository;
import com.nttdata.customer.service.customerservice.service.inter.EnterpriseCInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnterpriseService implements EnterpriseCInterface {

  @Autowired
  EnterpriseCustomerRepository enterpriseCustomerRepository;

  @Override
  public Mono<EnterpriseCustomer> create(EnterpriseCustomer request) {
    return enterpriseCustomerRepository.save(request);
  }

  @Override
  public Flux<EnterpriseCustomer> listAll() {
    return enterpriseCustomerRepository.findAll();
  }

  @Override
  public Mono<EnterpriseCustomer> getById(String id) {
    return enterpriseCustomerRepository.findById(id);
  }

  @Override
  public Mono<EnterpriseCustomer> update(String id, EnterpriseCustomer request) {
    return enterpriseCustomerRepository.findById(id)
        .flatMap(enterpriseCUpdate ->{
          enterpriseCUpdate.setBusinessName(request.getBusinessName());
          enterpriseCUpdate.setDocumentType(request.getDocumentType());
          enterpriseCUpdate.setRucNumber(request.getRucNumber());
          enterpriseCUpdate.setPhone(request.getPhone());
          enterpriseCUpdate.setEmail(request.getEmail());
          enterpriseCUpdate.setCustomerType(request.getCustomerType());
          enterpriseCUpdate.setCardNumber(request.getCardNumber());
          enterpriseCUpdate.setHeadlines(request.getHeadlines());
          enterpriseCUpdate.setAuthorizedSignatories(request.getAuthorizedSignatories());
          return enterpriseCustomerRepository.save(enterpriseCUpdate);
        });
  }

  @Override
  public Mono<Void> delete(String id) {
    return enterpriseCustomerRepository.deleteById(id);
  }
}
