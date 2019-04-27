package au.com.tabcorp.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CustomerTotal implements Serializable {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 2773196583361625761L;

	private final long customerId;
	private final BigDecimal total;
	
	public CustomerTotal(final long customerId, final double total) {
		this.customerId = customerId;
		this.total = new BigDecimal(total);
	}

	public long getCustomerId() {
		return customerId;
	}

	public BigDecimal getTotal() {
		return total;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
