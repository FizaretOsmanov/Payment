package com.code.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	private String userName;

	private String mobileNo;

	private String password;

	private String email;
	
	@JsonIgnore
	@OneToOne(optional = false, cascade = CascadeType.ALL)
	private Wallet wallet;

}
