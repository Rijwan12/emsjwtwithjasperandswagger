package net.javaguides.springboot.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

	static {
		inMemoryUserList.add(new JwtUserDetails(1L, "rijwan",
				"$2a$10$Hf2KotiSCTLzA7wi7DtSR..mKJw7Q3JPUXZYWRrPa.4aQOauEhidu", "ROLE_USER_2"));
		inMemoryUserList.add(new JwtUserDetails(2L, "khan",
				"$2a$10$ioKEM8JnGRUZoCRAKrSWS.iE.iSfI0FACP8skJOHrphtj6NyXaGwW", "ROLE_USER_2"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
				.filter(user -> user.getUsername().equals(username)).findFirst();

		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return findFirst.get();
	}

}
