package com.myapp.app;

import java.util.List;
import java.util.Optional;

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
@RequestMapping("/api/account")
public class AccountController {
	@Autowired
	private AccountRepo accRepo;
	
	/**
	 * Retrieves an account information given the account number
	 * @param accountNo
	 * @return the account information if present, else error
	 */
	@GetMapping("/{accountNo}")
	public ResponseEntity<Account> findAccountByAccountNo(@PathVariable(value = "accountNo") String accountNo){
		Optional<Account> user = Optional.of(accRepo.findByAccountNo(accountNo));
		if (user.isPresent()) {
			return ResponseEntity.ok().body(user.get()); // a 200 OK http response
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * Retrieves all the accounts for this customer
	 * @param custId
	 * @return
	 */
	@GetMapping("/myaccounts/{custId}")
	public List<Account> findAllAccountsByCustId(@PathVariable(value = "custId") String custId){
		return (List<Account>) accRepo.findByCustId(custId);
	}
	
	
	/**
	 * Checks for account balance for the given account no
	 * If the account number is correct, return the balance, otherwise return nothing
	 * @return Account balance
	 */
	@GetMapping("/balance/{accountNo}")
	public Double checkBalance(@PathVariable(value = "accountNo") String accountNo){
		Account account = accRepo.findByAccountNo(accountNo);
		if (account == null) {
			return 0.0;
		}else {
			return account.getBalance();
		}
	}
	
	/**
	 * Gets a new balance from the customer
	 * Retrieves the current balance from the passed account
	 * Adds the current balance with the provided amount
	 * Updates the record from the database
	 * @param accountNo
	 * @param amount
	 * @return Account details
	 */
	@PostMapping("/deposit")
	public ResponseEntity<Account> deposit(@Validated @RequestBody String accountNo, double amount) {
		double bal = checkBalance(accountNo);
		double updatedBal = bal + amount;
		accRepo.updateBalance(updatedBal, accountNo);
		return findAccountByAccountNo(accountNo);
	}
	
	/**
	 * Checks if the passed amount for withdrawal is less or equal to the current balance
	 * If so, withdraw and update the account data
	 * Otherwise, only return the account data as is
	 * @param accountNo
	 * @param amount
	 * @return Account Details
	 */
	@PostMapping("/withdraw")
	public ResponseEntity<Account> withdraw(@Validated @RequestBody String accountNo, double amount) {
		double bal = checkBalance(accountNo);
		if (bal >= amount) {
			double updatedBal = bal - amount;
			accRepo.updateBalance(updatedBal, accountNo);
		}
		return findAccountByAccountNo(accountNo);
	}
	
	/**
	 * Add a new account.
	 * 
	 * @param account
	 * @return
	 */
	@PostMapping("/addAccount")
	public Account saveAccount(@Validated @RequestBody Account account) {
		return accRepo.save(account);
	}
}
