package au.com.tabcorp.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.com.tabcorp.BetAcceptingServiceApplication;
import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.DateRange;
import au.com.tabcorp.repositories.BetRepository;
import au.com.tabcorp.test.fixture.TestFixture;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BetAcceptingServiceApplication.class, 
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BetStatControllerIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private BetRepository betRepository;
	
	@Test
	public void shouldReturnAverageBetPerHour() {
		//Given
		betRepository.deleteAll();
		Bet bet1 = TestFixture.getBet();
		bet1.setCreated(LocalDateTime.now().minusHours(2));
		betRepository.save(bet1);
		Bet bet2 = TestFixture.getBet();
		bet2.setCreated(LocalDateTime.now().minusHours(2));
		betRepository.save(bet2);
		Bet bet3 = TestFixture.getBet();
		bet3.setCreated(LocalDateTime.now().minusHours(2));
		betRepository.save(bet3);
		Bet bet4 = TestFixture.getBet();
		bet4.setCreated(LocalDateTime.now().minusHours(2));
		betRepository.save(bet4);
		Bet bet5 = TestFixture.getBet();
		bet5.setCreated(LocalDateTime.now().minusHours(2));
		betRepository.save(bet5);
		Bet bet6= TestFixture.getBet();
		bet6.setCreated(LocalDateTime.now().minusHours(2));
		betRepository.save(bet6);
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		DateRange dateRange = new DateRange();
		LocalDateTime now = LocalDateTime.now();
		dateRange.setTo(now);
		dateRange.setFrom(now.minusHours(2));
		
		HttpEntity<DateRange> entity = new HttpEntity<DateRange>(dateRange, 
				headers);
		//When
		ResponseEntity<BigDecimal> response = restTemplate
				.withBasicAuth("admin", "password")
				.exchange(
				createURLWithPort("/stat/averageBetPerHour"),
				HttpMethod.POST, entity, BigDecimal.class);
		
		//Then
		assertThat(response, is(notNullValue()));
		//6 bets in 2 hours, average 3 bets per hour
		assertThat(response.getBody(), is(equalTo(new BigDecimal(3))));
	}
	
	private String createURLWithPort(final String uri) {
		return "http://localhost:" + port + uri;
	}
}
