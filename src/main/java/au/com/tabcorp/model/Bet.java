package au.com.tabcorp.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
@Table(name="bet")
public class Bet implements Serializable {

	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = -4949949395251451863L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bet_id")
	private long betId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name="date_time")
	private LocalDateTime dateTime;
	@Column(name="bet_type")
	private BetType betType;
	@Column(name="prop_number")
	private long propNumber;
	@Column(name="customer_id")
	private long customerId;
	@Column(name="investment_amount")
	private double investmentAmount;
	@JsonIgnore
	@Column(name="created")
	private LocalDateTime created;

	public long getBetId() {
		return betId;
	}

	public void setBetId(long betId) {
		this.betId = betId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
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
	
	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
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
