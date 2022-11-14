package com.myapp.app;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	private CustomerRepo custRepo;
	
	/**
	 * Retrieve all customers
	 * @return list of all customers in the database
	 */
	@GetMapping("/all")
	public List<Customer> getAllCustomers(){
		return (List<Customer>) custRepo.findAll();
	}
	
	/**
	 * Retrieve all the customers based on the given customer id
	 * @param custId
	 * @return the Customer details or error
	 */
	@GetMapping("/{custId}")
	public ResponseEntity<Customer> findCustomerByCustId(@PathVariable(value = "custId") String custId){
		Optional<Customer> user = Optional.of(custRepo.findByCustId(custId));
		if (user.isPresent()) {
			return ResponseEntity.ok().body(user.get()); // a 200 OK http response
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * Create a new customer into the system
	 * @param customer
	 * @return Customer details of the saved customer
	 */
	@PostMapping("/addCustomer")
	public Customer addCustomer(@Validated @RequestBody Customer customer) {
		return custRepo.save(customer);
	}

}
