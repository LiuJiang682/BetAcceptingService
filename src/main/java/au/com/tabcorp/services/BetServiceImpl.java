package au.com.tabcorp.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.tabcorp.constants.Constants.Numeric;
import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetCount;
import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;
import au.com.tabcorp.model.CustomerTotal;
import au.com.tabcorp.model.DateRange;

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

	@Override
	public CustomerTotal getTotalInvestmentByCustomer(Long customerId) {
		return betPersistenceService.getTotalInvestmentByCustomer(customerId);
	}

	@Override
	public BetCount getBetCountByBetType(BetType betType) {
		return betPersistenceService.getBetCountByBetType(betType);
	}

	@Override
	public BigDecimal getAveragePerHourBetween(DateRange dateRange) {
		if (null == dateRange.getTo() ) {
			dateRange.setTo(LocalDateTime.now());
		}
		if (null == dateRange.getFrom()) {
			LocalDateTime to = dateRange.getTo();
			dateRange.setFrom(to.minusHours(Numeric.ONE));
		}
		Long hours = ChronoUnit.HOURS.between(dateRange.getFrom(), dateRange.getTo());
		if (hours <= Numeric.ZERO) {
			throw new IllegalArgumentException("The different between from and to must be at least 1!");
		}
		
		Long betCount = betPersistenceService.getBetCountByRange(dateRange.getFrom(), 
				dateRange.getTo());
		
		BigDecimal betCountTotal =  new BigDecimal(betCount);
		return betCountTotal.divideToIntegralValue(new BigDecimal(hours));
	}

}
