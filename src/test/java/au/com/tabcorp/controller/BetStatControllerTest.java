package au.com.tabcorp.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;
import au.com.tabcorp.services.BetService;
import au.com.tabcorp.test.fixture.TestFixture;

public class BetStatControllerTest {

	@Mock
	private BetService mockBetService;
	
	@InjectMocks
	private BetStatController testInstance;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldReturnTotalByBetType() {
		//Given
		BetType betType = BetType.WIN;
		when(mockBetService.getTotalInvestmentByBetType(eq(betType)))
			.thenReturn(TestFixture.getBetTypeTotal());
		//When
		ResponseEntity<BetTypeTotal> result = testInstance.getTotalInvestmentByBetType(betType);
		//Then
		assertThat(result, is(notNullValue()));
		BetTypeTotal betTypeTotal = result.getBody();
		assertThat(betTypeTotal.getBetType(), is(equalTo(betType)));
		assertThat(betTypeTotal.getTotal(), is(equalTo(new BigDecimal(200d))));
	}
}
