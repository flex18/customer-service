package com.nttdata.customer.service.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRequest {

  private String name;
  private String lastname;
  private String email;
  private CustomerType customerType;
  private String documentType;
  private String identificationNumber;
  private String cardNumber;
}
