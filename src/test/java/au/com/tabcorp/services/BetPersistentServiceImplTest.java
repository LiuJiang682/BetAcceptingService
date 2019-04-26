package au.com.tabcorp.services;

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

import au.com.tabcorp.model.BetType;
import au.com.tabcorp.model.BetTypeTotal;
import au.com.tabcorp.repositories.BetRepository;
import au.com.tabcorp.test.fixture.TestFixture;

public class BetPersistentServiceImplTest {

	@Mock
	private BetRepository mockBetRepository;
	
	@InjectMocks
	private BetPersistenceServiceImpl testInstance;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldCallGetTotalInvestmentByBetType() {
		//Given
		BetType betType = BetType.WIN;
		when(mockBetRepository.getTotalInvestmentByBetType(eq(betType)))
		.thenReturn(TestFixture.getBetTypeTotal());
		//When
		BetTypeTotal betTypeTotal = testInstance.getTotalInvestmentByBetType(betType);
		//Then
		assertThat(betTypeTotal, is(notNullValue()));
		assertThat(betTypeTotal.getBetType(), is(equalTo(BetType.WIN)));
		assertThat(betTypeTotal.getTotal(), is(equalTo(new BigDecimal(200d))));
	}
}
