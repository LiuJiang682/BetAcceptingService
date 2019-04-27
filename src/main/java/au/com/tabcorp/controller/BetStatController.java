package au.com.tabcorp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.tabcorp.model.BetCount;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;
import au.com.tabcorp.model.CustomerTotal;
import au.com.tabcorp.services.BetService;

@RestController
public class BetStatController {

	private static final Logger LOGGER = Logger.getLogger(BetStatController.class);
	
	@Autowired
	private BetService betService;
	
	@RequestMapping(value = "/stat/totalByType/{betType}", method = RequestMethod.GET)
	public ResponseEntity<BetTypeTotal> getTotalInvestmentByBetType(@PathVariable BetType betType) {
		LOGGER.info("About to get total investment for bet type: " + betType);
		BetTypeTotal betTypeTotal = betService.getTotalInvestmentByBetType(betType);
		return new ResponseEntity<BetTypeTotal>(betTypeTotal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stat/totalByCustomer/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<CustomerTotal> getTotalInvestmentByCustomer(@PathVariable Long customerId) {
		LOGGER.info("About to get total investment for customer " + customerId);
		CustomerTotal customerTotal = betService.getTotalInvestmentByCustomer(customerId);
		return new ResponseEntity<CustomerTotal>(customerTotal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stat/totalBetCountByType/{betType}", method = RequestMethod.GET)
	public ResponseEntity<BetCount> getBetCountByBetType(@PathVariable BetType betType) {
		LOGGER.info("Abount to get total bet count for bet type: " + betType);
		BetCount betCount = betService.getBetCountByBetType(betType);
		return new ResponseEntity<BetCount>(betCount, HttpStatus.OK);
	}
}
