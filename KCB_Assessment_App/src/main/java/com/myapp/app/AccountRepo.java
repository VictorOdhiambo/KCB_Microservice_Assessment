package com.myapp.app;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.lang.String;
import com.myapp.app.Account;
import java.util.List;

public interface AccountRepo extends CrudRepository<Account, Long> {
	Account findByAccountNo(String accountno);
	List<Account> findByCustId(String custid);

	@Modifying
	@Query("UPDATE Account a SET a.balance = :newbal WHERE a.accountNo = :accountno")
	int updateBalance(@Param("newbal") double newbal, @Param("accountno") String accountno);
}
