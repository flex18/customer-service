package com.nttdata.customer.service.customerservice.service.impl;

import com.nttdata.customer.service.customerservice.configuration.http.BAMProperties;
import com.nttdata.customer.service.customerservice.entity.CustomerRequest;
import com.nttdata.customer.service.customerservice.entity.CustomerResponse;
import com.nttdata.customer.service.customerservice.entity.bam.BankAccount;
import com.nttdata.customer.service.customerservice.repository.CustomerRepository;
import com.nttdata.customer.service.customerservice.service.inter.CustomerInterface;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Primary
@Service
public class CustomerService implements CustomerInterface {

  @Autowired
  BAMProperties bamProperties;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Mono<CustomerResponse> create(CustomerRequest request) {
    CustomerResponse customer = new CustomerResponse(request);
    return customerRepository.save(customer);
  }

  @Override
  public Flux<CustomerResponse> listAll() {
    return customerRepository.findAll();
  }

  @Override
  public Mono<CustomerResponse> getById(String id) {
    Optional<CustomerResponse> optionalCustomer = Optional
        .ofNullable(customerRepository.findById(id).block());
    return optionalCustomer.map(Mono::just).orElseGet(Mono::empty);
  }

  @Override
  public Mono<CustomerResponse> update(String id, CustomerRequest request) {
    return customerRepository.findById(id)
        .flatMap(customerUpdate -> {
          customerUpdate.setName(request.getName());
          customerUpdate.setLastname(request.getLastname());
          customerUpdate.setEmail(request.getEmail());
          customerUpdate.setCustomerType(request.getCustomerType());
          customerUpdate.setDocumentType(request.getDocumentType());
          customerUpdate.setIdentificationNumber(request.getIdentificationNumber());
          customerUpdate.setCardNumber(request.getCardNumber());
          return customerRepository.save(customerUpdate);
        });
  }

  @Override
  public Mono<Void> delete(String id) {
    return customerRepository.deleteById(id);
  }

  public Mono<BankAccount> openBankAccount(String customerId, BankAccount bankAccount) {
    String url = bamProperties.getUrl();
    bankAccount.setCustomerId(customerId);
    return Mono.just(Objects.requireNonNull(restTemplate.postForObject(url, bankAccount, BankAccount.class)));
  }

  public Mono<BankAccount> showBankAccount(String accountId){
    String url = bamProperties.getUrl() + "/" + accountId;
    log.info("*** The url for get bank account is: " + url);
    try {
      BankAccount bankAccount = restTemplate.getForObject(url, BankAccount.class);
      return Mono.justOrEmpty(bankAccount);
    } catch (Exception e) {
      log.error("Error while fetching bank account: " + e.getMessage());
      return Mono.error(e);
    }
  }


}
