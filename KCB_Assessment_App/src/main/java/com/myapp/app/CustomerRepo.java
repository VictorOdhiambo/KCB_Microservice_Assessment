package com.myapp.app;

import org.springframework.data.repository.CrudRepository;
import java.lang.String;
import com.myapp.app.Customer;
public interface CustomerRepo extends CrudRepository<Customer, Long>{
	Customer findByCustId(String custid);
}
