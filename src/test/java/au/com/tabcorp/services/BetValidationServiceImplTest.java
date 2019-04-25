package au.com.tabcorp.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import au.com.tabcorp.model.Bet;
import au.com.tabcorp.services.validator.DateTimeValidator;
import au.com.tabcorp.services.validator.MaxAmountValidator;
import au.com.tabcorp.test.fixture.TestFixture;

public class BetValidationServiceImplTest {

	@Mock
	private MaxAmountValidator mockMaxAmountValidator;
	@Mock
	private DateTimeValidator mockDateTimeValidator;
	
	@InjectMocks
	private BetValidationServiceImpl testInstance;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldInvokeMaxAmountAndDateTimeValidatorWhenValidateCalled() {
		//Given
		Bet bet = TestFixture.getBet();
		//When
		testInstance.validate(bet);
		//Then
		ArgumentCaptor<Bet> maxAmountCaptor = ArgumentCaptor.forClass(Bet.class);
		ArgumentCaptor<Bet> dateTimeCaptor = ArgumentCaptor.forClass(Bet.class);
		verify(mockMaxAmountValidator).validate(maxAmountCaptor.capture());
		verify(mockDateTimeValidator).validate(dateTimeCaptor.capture());
		List<Bet> capturedMaxAmountBets = maxAmountCaptor.getAllValues();
		assertThat(capturedMaxAmountBets.size(), is(equalTo(1)));
		assertThat(capturedMaxAmountBets.get(0), is(equalTo(bet)));
		List<Bet> capturedDateTimeBets = dateTimeCaptor.getAllValues();
		assertThat(capturedDateTimeBets.size(), is(equalTo(1)));
		assertThat(capturedDateTimeBets.get(0), is(equalTo(bet)));
	}
}
