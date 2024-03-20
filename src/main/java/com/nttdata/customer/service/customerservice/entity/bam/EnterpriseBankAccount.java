package com.nttdata.customer.service.customerservice.entity.bam;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnterpriseBankAccount {

  private String accountId;
  private EnterpriseAccountType accountType; //corriente
  private String customerId;
  private BigDecimal amount;
  private String cellPhoneNumber;
}
