package com.nttdata.customer.service.customerservice.entity.enterprise;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EnterpriseCustomerType {

  @JsonProperty("BUSINESS")
  BUSINESS("BUSINESS"),
  @JsonProperty("SME_BUSINESS")
  SME_BUSINESS("SME_BUSINESS");

  private final String value;

  EnterpriseCustomerType(String value) {this.value = value; }

  public static EnterpriseCustomerType fromValue(String text) {
    for (EnterpriseCustomerType b : EnterpriseCustomerType.values()) {
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
