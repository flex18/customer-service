package com.nttdata.customer.service.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document("customer")
public class CustomerResponse {

  @Id
  private String id;
  private String name;
  private String lastname;
  private String phone;
  private String email;
  private CustomerType customerType;
  private String documentType;
  private String cardNumber;
  private String startDate;

  public CustomerResponse(CustomerRequest create) {
    id = UUID.randomUUID().toString();
    name = create.getName();
    lastname = create.getLastname();
    phone = create.getPhone();
    email = create.getEmail();
    customerType = create.getCustomerType();
    documentType = create.getDocumentType();
    cardNumber = create.getCardNumber();
    startDate = new DateTime().toDateTimeISO().toString();
  }
}
