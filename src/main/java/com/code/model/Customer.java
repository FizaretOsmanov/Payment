package com.code.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private String userName;

	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Invalid email format")
	private String email;

	private Double balance;

	@NotNull
	@Size(min = 10, max = 10)
	@Pattern(regexp = "^\\+994-(50|51|55|70|77)-\\d{3}-\\d{2}-\\d{2}$", message = "Invalid phone number format")
	private String mobileNo;

	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9]{6,12}", message = "Password must contain between 6 to 12 characters. " +
			"Must be alphanumeric with both Upper and lowercase characters.")
	private String password;
	
	@JsonIgnore
	@OneToOne(optional = false, cascade = CascadeType.ALL)
	private Wallet wallet;

}
