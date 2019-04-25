package au.com.tabcorp.services.validator;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import au.com.tabcorp.constants.Constants.Numeric;
import au.com.tabcorp.model.Bet;

/**
 * This class performs max amount validation using Spring property due to
 * its low cost compare to access the database to retrieve the max allow amount. 
 * An additional class can be created and wired to using database if max allow
 * amount requires dynamic update.
 * 
 * @author Jiang Liu
 *
 */
@Component
public class MaxAmountPropertyValidator implements MaxAmountValidator {

	@Value("${config.max.allow:20000}")
	private String maxAllows;
	
	@Override
	public void validate(Bet bet) {
		BigDecimal maxAllowsAmount = new BigDecimal(maxAllows);
		BigDecimal betAmount = new BigDecimal(bet.getInvestmentAmount());
		if (maxAllowsAmount.compareTo(betAmount) < Numeric.ZERO) {
			//Exceed max
			throw new IllegalArgumentException("The investment amount exceeded maximum allows!");
		}
	}

}
