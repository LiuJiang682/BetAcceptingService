package au.com.tabcorp.services.validator;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

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
public class MaxAmountPropertyValidatorTest {

	@Autowired
	private MaxAmountPropertyValidator testInstance;

	@Test
	public void shouldReturnInstance() {
		assertThat(testInstance, is(notNullValue()));
	}

	@Test
	public void shouldPassMaxAllowTestWhenAmountIs2000() {
		// Given
		Bet bet = TestFixture.getBet();
		bet.setInvestmentAmount(2000d);
		// When
		testInstance.validate(bet);
		// Then
		assertThat(true, is(true));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenAmountIs2001() {
		// Given
		Bet bet = TestFixture.getBet();
		bet.setInvestmentAmount(2001d);
		// When
		testInstance.validate(bet);
		fail("Program reached unexpected point!");
	}
}
