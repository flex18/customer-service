package com.nttdata.customer.service.customerservice.entity.enterprise;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document("enterpriseCustomer")
public class EnterpriseCustomer implements Serializable {

  @Id
  private String id;
  private String businessName;
  private String documentType;
  private String rucNumber;
  private String phone;
  private String email;
  private EnterpriseCustomerType customerType;
  private String cardNumber;
  private List<Person> headlines;
  private List<Person> authorizedSignatories;
  private String startDate;
}
