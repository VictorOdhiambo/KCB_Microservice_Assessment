package com.myapp.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card")
public class CardController {
	@Autowired
	private CardRepo cardRepo;
	
	
	/**
	 * Retrieve all the customers based on the given customer id
	 * @param custId
	 * @return the Customer details or error
	 */
	@GetMapping("/{Id}")
	public ResponseEntity<Card> findCardByCustId(@PathVariable(value = "Id") long id){
		Optional<Card> card = cardRepo.findById(id);
		if (card.isPresent()) {
			return ResponseEntity.ok().body(card.get()); // a 200 OK http response
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Retrieves all the cards for this customer
	 * @param custId
	 * @return
	 */
	@GetMapping("/mycards/{custId}")
	public List<Card> findAllAccountsByCustId(@PathVariable(value = "custId") String custId){
		return (List<Card>) cardRepo.findByCustId(custId);
	}
}
