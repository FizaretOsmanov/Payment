package com.code.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "csu")
public class CurrentSessionUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(unique =  true)
	private Integer userId;
	
	private String uuid;
	
	private String mobileNo;
	
	private LocalDateTime localDateTime;

	public CurrentSessionUser(Integer userId, String uuid, String mobileNo, LocalDateTime localDateTime) {
		super();
		this.userId = userId;
		this.uuid = uuid;
		this.mobileNo = mobileNo;
		this.localDateTime = localDateTime;
	}

	@Override
	public String toString() {
		return "CurrentSessionUser [id=" + id + ", userId=" + userId + ", uuid=" + uuid + ", localDateTime="
				+ localDateTime + "]";
	}
	
	

	
}
