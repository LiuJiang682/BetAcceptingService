package au.com.tabcorp.services.validator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.test.fixture.TestFixture;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DateTimeValidatorImplTest {

	@Autowired
	private DateTimeValidatorImpl testInstance;
	
	@Test
	public void shouldPassValidationWhenTheTimeIsInFuture() {
		//Given
		Bet bet = TestFixture.getBet();
		//When
		testInstance.validate(bet);
		//Then
		assertThat(true, is(true));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenTheTimeIsNull() {
		//Given
		Bet bet = TestFixture.getBet();
		bet.setDateTime(null);
		//When
		testInstance.validate(bet);
		fail("Program reached unexpected point!");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenTheTimeIsInPast() {
		//Given
		Bet bet = TestFixture.getBet();
		bet.setDateTime(null);
		//When
		testInstance.validate(bet);
		fail("Program reached unexpected point!");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenTheTimeFormatIsUnknown() {
		//Given
		Bet bet = TestFixture.getBet();
		bet.setDateTime("44ddf");
		//When
		testInstance.validate(bet);
		fail("Program reached unexpected point!");
	}
}
