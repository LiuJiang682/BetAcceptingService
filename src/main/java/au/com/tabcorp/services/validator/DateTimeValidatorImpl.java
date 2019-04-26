package au.com.tabcorp.services.validator;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import au.com.tabcorp.model.Bet;

@Component
public class DateTimeValidatorImpl implements DateTimeValidator {

	@Value("${config.datetime.format:yyyy-MM-dd HH:mm:ss}")
	private String dateTimeFormat;
	
	@Override
	public void validate(Bet bet) {
		LocalDateTime dateTime = bet.getDateTime();
		if (null == dateTime) {
			throw new IllegalArgumentException("DateTime cannot be null!");
		} else {
			if (dateTime.isBefore(LocalDateTime.now())) {
				throw new IllegalArgumentException("DateTime cannot be in the past!");
			}
		}
	}

}
