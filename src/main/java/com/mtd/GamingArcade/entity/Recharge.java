package com.mtd.GamingArcade.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "recharges")
public class Recharge {
    @Id
    private String id;
    private String memberId;
    private Double amount;
    private LocalDateTime dateTime = LocalDateTime.now();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	@Override
	public String toString() {
		return "Recharge [id=" + id + ", memberId=" + memberId + ", amount=" + amount + ", dateTime=" + dateTime + "]";
	}
	public Recharge(String id, String memberId, Double amount, LocalDateTime dateTime) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.amount = amount;
		this.dateTime = dateTime;
	}
	public Recharge() {
		super();
	}
}