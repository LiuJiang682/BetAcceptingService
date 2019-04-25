package au.com.tabcorp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.services.validator.DateTimeValidator;
import au.com.tabcorp.services.validator.MaxAmountValidator;

@Service
public class BetValidationServiceImpl implements BetValidationService {

	@Autowired
	private MaxAmountValidator maxAmountValidator;
	@Autowired
	private DateTimeValidator dateTimeValidator;
	
	@Override
	public void validate(Bet bet) {
		maxAmountValidator.validate(bet);
		dateTimeValidator.validate(bet);
	}

}
