package com.nttdata.customer.service.customerservice.entity.bam;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EnterpriseAccountType {

  @JsonProperty("CURRENT_ACCOUNT")
  CURRENT_ACCOUNT("CURRENT_ACCOUNT");

  private final String value;

  EnterpriseAccountType(String value) {this.value = value; }

  public static EnterpriseAccountType fromValue(String text) {
    for (EnterpriseAccountType b : EnterpriseAccountType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

}
