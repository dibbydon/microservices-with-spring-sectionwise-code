/**
 * 
 */
package com.dibbydon.cards.controller;

import java.util.List;

import com.dibbydon.cards.config.CardsServiceConfig;
import com.dibbydon.cards.model.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dibbydon.cards.model.Cards;
import com.dibbydon.cards.model.Customer;
import com.dibbydon.cards.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author Eazy Bytes
 *
 */

@RestController
public class CardsController {
	
	private static final Logger logger = LoggerFactory.getLogger(CardsController.class);

	@Autowired
	private CardsRepository cardsRepository;
	
	@Autowired
    CardsServiceConfig cardsConfig;

	@PostMapping("/myCards")
	public List<Cards> getCardDetails(@RequestHeader("eazybank-correlation-id") String correlationid,@RequestBody Customer customer) {
		logger.info("getCardDetails() method started");
		List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
		logger.info("getCardDetails() method ended");
		if (cards != null) {
			return cards;
		} else {
			return null;
		}

	}
	
	@GetMapping("/cards/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(cardsConfig.getMsg(), cardsConfig.getBuildVersion(),
				cardsConfig.getMailDetails(), cardsConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}

}
