package au.com.tabcorp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.services.BetService;

@RestController
public class BetController {

	private static final Logger LOGGER = Logger.getLogger(BetController.class);
	
	@Autowired
	private BetService betService;
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Bet> placeBet(@RequestBody Bet bet) {
		LOGGER.info("Recieved bet: " + bet);
		betService.placeBet(bet);
		return new ResponseEntity<Bet>(bet, HttpStatus.OK);
	}
}
