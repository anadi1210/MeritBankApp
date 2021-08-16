package com.meritbank.v1.meritBankV1.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.meritbank.v1.meritBankV1.models.User;
import com.meritbank.v1.meritBankV1.repos.UserRepository;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
 
  
  @Autowired
  UserRepository userRepository;
  
  static {
		/*
		 * inMemoryUserList.add(new JwtUserDetails(1L, "anadi",
		 * "$2a$10$7KXglhyGFHkP6Scjz.duh.wPKXL58qR3in3PjiZQT2EfmVX/lqXsq",
		 * "ROLE_USER_2")); inMemoryUserList.add(new JwtUserDetails(2L, "test",
		 * "$2a$10$7KXglhyGFHkP6Scjz.duh.wPKXL58qR3in3PjiZQT2EfmVX/lqXsq",
		 * "ROLE_USER_2")); inMemoryUserList.add(new JwtUserDetails(3L, "pamela", new
		 * BCryptPasswordEncoder().encode("123"), "ROLE_USER_2"));
		 */
	 
  }
  
  public  void setUsers() {
	  
	  List<User> usersList = userRepository.findAll();
	  
	  for(int i = 0 ; i < usersList.size() ; i++) {
		  String username = usersList.get(i).getUsername();
		  String password = usersList.get(i).getPassword();
		  String role = usersList.get(i).getRoles();
		  
		  new BCryptPasswordEncoder().encode(password);
		  
		  inMemoryUserList.add
		  	(new JwtUserDetails(i+1L, username, new BCryptPasswordEncoder().encode(password), role));
	  }
	  
	  
	  //return userRepository.findAll();
  }

  // password 123 = $2a$10$7KXglhyGFHkP6Scjz.duh.wPKXL58qR3in3PjiZQT2EfmVX/lqXsq
  
  @Override
  public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  setUsers();
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


