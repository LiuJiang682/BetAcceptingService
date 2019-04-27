package au.com.tabcorp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetCount;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;
import au.com.tabcorp.model.CustomerTotal;
import au.com.tabcorp.repositories.BetRepository;

@Service
public class BetPersistenceServiceImpl implements BetPersistenceService {

	@Autowired
	private BetRepository betRepository;
	
	@Override
	public void save(Bet bet) {
		betRepository.save(bet);
	}

	@Override
	public BetTypeTotal getTotalInvestmentByBetType(BetType betType) {
		return betRepository.getTotalInvestmentByBetType(betType);
	}

	@Override
	public CustomerTotal getTotalInvestmentByCustomer(Long customerId) {
		return betRepository.getTotalInvestmentByCustomer(customerId);
	}

	@Override
	public BetCount getBetCountByBetType(BetType betType) {
		return betRepository.getBetCountByBetType(betType);
	}

}
