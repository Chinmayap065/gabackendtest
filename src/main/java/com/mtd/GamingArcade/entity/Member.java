package com.mtd.GamingArcade.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "members")
public class Member {
    @Id
    private String id; // In MongoDB, the ID is typically a String (ObjectId)
    private String name;
    private Double balance = 0.0;
    private String phone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", balance=" + balance + ", phone=" + phone + "]";
	}
	public Member(String id, String name, Double balance, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.phone = phone;
	}
	public Member() {
		super();
	}
}