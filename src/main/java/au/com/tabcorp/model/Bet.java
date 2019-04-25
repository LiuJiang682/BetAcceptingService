package au.com.tabcorp.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Bet implements Serializable {

	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = -4949949395251451863L;
	

	private String dateTime;
	private BetType betType;
	private long propNumber;
	private long customerId;
	private double investmentAmount;

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public long getPropNumber() {
		return propNumber;
	}

	public void setPropNumber(long propNumber) {
		this.propNumber = propNumber;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public double getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(double investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public BetType getBetType() {
		return betType;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
