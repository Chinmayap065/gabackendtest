package com.mtd.GamingArcade.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private String memberId;
    private String gameId;
    private String gameName; // Denormalized for easier history viewing
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
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
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
		return "Transaction [id=" + id + ", memberId=" + memberId + ", gameId=" + gameId + ", gameName=" + gameName
				+ ", amount=" + amount + ", dateTime=" + dateTime + "]";
	}
	public Transaction(String id, String memberId, String gameId, String gameName, Double amount,
			LocalDateTime dateTime) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.gameId = gameId;
		this.gameName = gameName;
		this.amount = amount;
		this.dateTime = dateTime;
	}
	public Transaction() {
		super();
	}
}