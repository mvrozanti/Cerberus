package yet.another.test.spring.fucking.boot;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	JdbcTemplate jdbcTemplate;
//	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = this.userRepository.findByUsername(username);
		User user = jdbcTemplate.query("SELECT idc_usuario, psw_usuario FROM bd_4Config..tab_usuario where idc_usuario=?", new Object[]{username}, (rs, row) -> new User(rs.getString("idc_usuario"), rs.getString("psw_usuario"), "", new Date(), "admin")).get(0);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return Sys4BankUserFactory.create(user);
		}
	}

}
