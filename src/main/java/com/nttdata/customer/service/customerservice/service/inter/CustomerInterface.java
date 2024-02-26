package com.nttdata.customer.service.customerservice.service.inter;

import com.nttdata.customer.service.customerservice.entity.CustomerRequest;
import com.nttdata.customer.service.customerservice.entity.CustomerResponse;
import java.util.List;

public interface CustomerInterface {

  CustomerResponse add(CustomerRequest request);

  List<CustomerResponse> list();

  CustomerResponse getById(String id);

  CustomerResponse edit(CustomerRequest request);

  void delete(String id);
}
