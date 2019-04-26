package au.com.tabcorp.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import au.com.tabcorp.converter.BetMessageConverter;
import au.com.tabcorp.model.Bet;
import au.com.tabcorp.model.BetType;

public class BetControllerBinaryIT {
	//This test services as an convenient method to perform direct
	//test on binary input. Thus, it marked as ignore prevent normal
	//test run
	@Ignore
	@Test
    public void shouldAcceptBinary() throws Exception {
		//Given
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder().additionalMessageConverters(new BetMessageConverter());
        TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplateBuilder);

        Bet bet = new Bet();
        bet.setBetType(BetType.WIN);
        bet.setInvestmentAmount(200d);
        bet.setDateTime(LocalDateTime.now().plusDays(1));
        String uri = "http://localhost:8080/binary";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
        //When
        ResponseEntity<Bet> responseEntity = testRestTemplate.exchange(uri, HttpMethod.PUT, 
        		new HttpEntity<>(bet, headers), Bet.class);
        //Then
        assertThat(responseEntity, is(notNullValue()));
        Bet savedBet = responseEntity.getBody();
        assertThat(savedBet.getInvestmentAmount(), is(equalTo(bet.getInvestmentAmount())));
	}
}
