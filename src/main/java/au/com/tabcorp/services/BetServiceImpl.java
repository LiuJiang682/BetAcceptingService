package au.com.tabcorp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;

@Service
public class BetServiceImpl implements BetService {
	
	@Autowired
	private BetValidationService betValidationService;
	@Autowired
	private BetPersistenceService betPersistenceService;

	@Override
	public void placeBet(Bet bet) {
		betValidationService.validate(bet);
		betPersistenceService.save(bet);
	}

	@Override
	public BetTypeTotal getTotalInvestmentByBetType(BetType betType) {
		return betPersistenceService.getTotalInvestmentByBetType(betType);
	}

}
