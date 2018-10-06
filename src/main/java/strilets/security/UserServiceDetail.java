/**
 * Class for user authentication.
 */
package strilets.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import strilets.model.User;
import strilets.service.UserService;

@Service("userServiceDetail")

public class UserServiceDetail implements UserDetailsService {

	static final Logger logger = LoggerFactory.getLogger(UserServiceDetail.class);

	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = userService.getUserByLogin(login);
		logger.info("User: {}", login);
		if (user == null) {
			logger.info("User not found.");
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true,
				true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		logger.info("User: {}", user.getLogin());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
		logger.info("Authorities: {}", authorities);
		return authorities;
	}
}