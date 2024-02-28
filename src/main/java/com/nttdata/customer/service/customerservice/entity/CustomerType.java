package com.nttdata.customer.service.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CustomerType {

  @JsonProperty("PERSONAL_CLIENT")
  PERSONAL_CLIENT("PERSONAL_CLIENT"),
  @JsonProperty("VIP_PERSONAL_CLIENT")
  VIP_PERSONAL_CLIENT("VIP_PERSONAL_CLIENT"),
  @JsonProperty("BUSINESS_CLIENT")
  BUSINESS_CLIENT("BUSINESS_CLIENT"),
  @JsonProperty("SME_BUSINESS_CLIENT")
  SME_BUSINESS_CLIENT("SME_BUSINESS_CLIENT");

  private final String value;

  CustomerType(String value) {this.value = value; }

  public static CustomerType fromValue(String text) {
    for (CustomerType b : CustomerType.values()) {
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
