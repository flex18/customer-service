package com.nttdata.customer.service.customerservice.controller;

import com.nttdata.customer.service.customerservice.entity.CustomerRequest;
import com.nttdata.customer.service.customerservice.entity.CustomerResponse;
import com.nttdata.customer.service.customerservice.entity.bam.BankAccount;
import com.nttdata.customer.service.customerservice.service.impl.CustomerService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @PostMapping
  public Mono<ResponseEntity<CustomerResponse>> createCustomer(@RequestBody CustomerRequest request) {
    return customerService.create(request)
        .map(customerResponse -> new ResponseEntity<>(customerResponse, HttpStatus.CREATED));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<CustomerResponse>> getCustomerById(@PathVariable String id) {
    return customerService.getById(id)
        .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping
  public Flux<CustomerResponse> getAllCustomers() {
    return customerService.listAll();
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<CustomerResponse>> updateCustomer(@PathVariable String id, @RequestBody
      CustomerRequest customerRequest) {
    return customerService.update(id, customerRequest)
        .map(customerResponse -> new ResponseEntity<>(customerResponse, HttpStatus.OK))
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable String id) {
    return customerService.delete(id)
        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/{customerId}/bank-account")
  public Mono<BankAccount> openBankAccount(@PathVariable String customerId, @RequestBody BankAccount bankAccount) {
    return customerService.openBankAccount(customerId, bankAccount);
  }

  @GetMapping("/show-bank-account/{accountId}")
  public Mono<BankAccount> showBankAccount(@PathVariable String accountId) throws InterruptedException {
    if (accountId.equals("10")){
      throw new IllegalStateException("bank account not found");
    }
    if (accountId.equals("7")){
      TimeUnit.SECONDS.sleep(5L);
    }
    return customerService.showBankAccount(accountId);
  }
}
