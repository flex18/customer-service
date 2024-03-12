package com.nttdata.customer.service.customerservice.controller;

import com.nttdata.customer.service.customerservice.entity.bam.EnterpriseBankAccount;
import com.nttdata.customer.service.customerservice.entity.enterprise.EnterpriseCustomer;
import com.nttdata.customer.service.customerservice.service.impl.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/enterprise-customer")
public class EnterpriseCustomerController {

  @Autowired
  EnterpriseService enterpriseService;

  @GetMapping
  public Flux<EnterpriseCustomer> getAllEnterpriseCustomer() {
    return enterpriseService.listAll();
  }

  @PostMapping
  public Mono<EnterpriseCustomer> createEnterpriseCustomer(@RequestBody EnterpriseCustomer request) {
    return enterpriseService.create(request);
  }

  @GetMapping("/{id}")
  public Mono<EnterpriseCustomer> getByIdEnterpriseCustomer(@PathVariable String id) {
    return enterpriseService.getById(id);
  }

  @PutMapping("/id")
  public Mono<EnterpriseCustomer> updateEnterpriseCustomer(@PathVariable String id, @RequestBody EnterpriseCustomer request) {
    return enterpriseService.update(id, request);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deleteEnterpriseCustomer(@PathVariable String id) {
    return enterpriseService.delete(id);
  }

  @PostMapping("/{clientId}/enterprise-bank-account")
  public Mono<EnterpriseBankAccount> openEnterpriseBankAccount(@PathVariable String clientId, @RequestBody EnterpriseBankAccount request){
    return enterpriseService.openEnterpriseBankAccount(clientId, request);
  }
  @GetMapping("/show-bank-account/{clientId}")
  public Mono<EnterpriseBankAccount> showEnterpriseBankAccount(@PathVariable String clientId){
    return enterpriseService.showEnterpriseBankAccount(clientId);
  }
}
