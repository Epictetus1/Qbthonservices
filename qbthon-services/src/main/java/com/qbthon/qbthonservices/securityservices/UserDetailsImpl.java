package com.qbthon.qbthonservices.securityservices;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.qbthon.qbthonservices.models.Users;
public class UserDetailsImpl implements UserDetails {
	private Collection<? extends GrantedAuthority> authorities;
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public UserDetailsImpl(String userName, String email, String password, List<GrantedAuthority> authorities) {
		this.username = userName;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String username;

	private String email;

	private String password;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public static UserDetailsImpl build(Users user) {
		List<GrantedAuthority> authorities = user.getRole().stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
         System.out.println("inside userdetailsimpl");
         System.out.println(user.getUserName()+" "+user.getPassword());
		return new UserDetailsImpl(
				 
				user.getUserName(), 
				user.getEmail(),
				user.getPassword(),
				authorities);
	}

}
