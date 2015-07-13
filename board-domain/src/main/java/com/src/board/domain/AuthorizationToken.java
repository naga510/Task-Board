package com.src.board.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 * @author Nagaraju Makkena
 * @version 1.0
 */
@Entity
@Table(name="USER_AUTHORIZATION_TOKEN")
public class AuthorizationToken implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7628657124796347897L;
	
	private final static long DEFAULT_TIME_TO_LIVE_IN_SECONDS = (60 * 2);
	private String id;
    private String token;
    private LocalDateTime timeCreated;
    private LocalDateTime expirationDate;
    private User user;
    
    @PrePersist
	public void prePersist() {
    	id = UUID.randomUUID().toString();
    	token = UUID.randomUUID().toString();
    	timeCreated = LocalDateTime.now();
		expirationDate = LocalDateTime.now().plusSeconds(DEFAULT_TIME_TO_LIVE_IN_SECONDS);
	}
        
    @Id
    @Column(name="auth_id", nullable=false)
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
    @Column(name="token", nullable=false)
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Column(name="timeCreated", nullable=false)
	public LocalDateTime getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(LocalDateTime timeCreated) {
		this.timeCreated = timeCreated;
	}
     
	@Column(name="expirationDate", nullable=false)
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

    @JoinColumn(name="ID", nullable=false)
    @OneToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	 public boolean hasExpired() {
	        return this.expirationDate != null && this.expirationDate.isBefore(LocalDateTime.now());
	    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result
				+ ((timeCreated == null) ? 0 : timeCreated.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorizationToken other = (AuthorizationToken) obj;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (timeCreated == null) {
			if (other.timeCreated != null)
				return false;
		} else if (!timeCreated.equals(other.timeCreated))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AuthorizationToken [token=" + token + ", timeCreated="
				+ timeCreated + ", expirationDate=" + expirationDate + "]";
	}

	
    
    


}
