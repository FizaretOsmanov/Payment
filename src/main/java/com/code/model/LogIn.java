package com.code.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LogIn {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	private String mobileNo;

	private String password;


}
