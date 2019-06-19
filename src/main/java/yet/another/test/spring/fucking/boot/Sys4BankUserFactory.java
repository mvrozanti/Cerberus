package yet.another.test.spring.fucking.boot;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class Sys4BankUserFactory {

	public static Sys4BankUser create(User user) {
		Collection<? extends GrantedAuthority> authorities;
		try {
			authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
		} catch (Exception e) {
			authorities = null;
		}
		return new Sys4BankUser(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				user.getEmail(),
				user.getLastPasswordReset(),
				authorities
		);
	}

}
