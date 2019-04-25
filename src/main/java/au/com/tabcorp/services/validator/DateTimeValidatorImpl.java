package au.com.tabcorp.services.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import au.com.tabcorp.model.Bet;

@Component
public class DateTimeValidatorImpl implements DateTimeValidator {

	@Value("${config.datetime.format:yyyy-MM-dd HH:mm:ss}")
	private String dateTimeFormat;
	
	@Override
	public void validate(Bet bet) {
		String dateTimeString = bet.getDateTime();
		if (null == dateTimeString) {
			throw new IllegalArgumentException("DateTime cannot be null!");
		} else {
			
			SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
			
			try {
				Date dateTime = sdf.parse(dateTimeString);
				if(dateTime.before(new Date())) {
					throw new IllegalArgumentException("DateTime cannot be in the past!");
				}
			} catch (ParseException e) {
				throw new IllegalArgumentException("Unknow date time format");
			}
			
		}
	}

}
