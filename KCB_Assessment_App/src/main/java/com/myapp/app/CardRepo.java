package com.myapp.app;

import org.springframework.data.repository.CrudRepository;
import java.lang.String;
import com.myapp.app.Card;
import java.util.List;

public interface CardRepo extends CrudRepository<Card, Long>{
	List<Card> findByCustId(String custid);
}
