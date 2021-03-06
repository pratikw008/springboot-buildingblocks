package com.app.user.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USERS_TABLE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@NotEmpty(message = "Username is mandatory. plz provide username")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	private String username;
	
	@Size(min = 2, message = "FirstName should have atleast 2 characters")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String lastName;
	
	@Column(name = "EMAIL", length = 50, nullable = false)
	private String email;
	
	@Column(name = "ROLE", length = 50, nullable = false)
	private String role;
	
	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	private String ssn;
	
	@OneToMany(mappedBy = "userEntity")
	private List<OrderEntity> orders;
}
