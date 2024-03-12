package com.nttdata.customer.service.customerservice.service.impl;

import com.nttdata.customer.service.customerservice.configuration.http.BAMProperties;
import com.nttdata.customer.service.customerservice.entity.bam.EnterpriseBankAccount;
import com.nttdata.customer.service.customerservice.entity.enterprise.EnterpriseCustomer;
import com.nttdata.customer.service.customerservice.repository.EnterpriseCustomerRepository;
import com.nttdata.customer.service.customerservice.service.inter.EnterpriseCInterface;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class EnterpriseService implements EnterpriseCInterface {

  @Autowired
  EnterpriseCustomerRepository enterpriseCustomerRepository;

  @Autowired
  BAMProperties bamProperties;

  @Autowired
  RestTemplate restTemplate;

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
    Optional<EnterpriseCustomer> optionalEnterpriseCustomer = Optional
        .ofNullable(enterpriseCustomerRepository.findById(id).block());
    return optionalEnterpriseCustomer.map(Mono::just).orElseGet(Mono::empty);

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

  public Mono<EnterpriseBankAccount> openEnterpriseBankAccount(String clientId, EnterpriseBankAccount enterpriseBankAccount) {
    String url = bamProperties.getUrl();
    enterpriseBankAccount.setCustomerId(clientId);
    return Mono.just(Objects.requireNonNull(restTemplate.postForObject(url, enterpriseBankAccount, EnterpriseBankAccount.class)));
  }

  public Mono<EnterpriseBankAccount> showEnterpriseBankAccount(String clientId){
    String url = bamProperties.getUrl() + "/" + clientId;
    log.info("*** The url for get enterprise bank account is: " + url);
    try {
      EnterpriseBankAccount enterpriseBankAccount = restTemplate.getForObject(url, EnterpriseBankAccount.class);
      return Mono.justOrEmpty(enterpriseBankAccount);
    } catch (Exception e) {
      log.error("Error while fetching enterprise bank account: " + e.getMessage());
      return Mono.error(e);
    }
  }

}
