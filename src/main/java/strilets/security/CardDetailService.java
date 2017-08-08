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
import org.springframework.transaction.annotation.Transactional;

import strilets.model.Card;
import strilets.service.CardService;

@Service("cardDetailsService")
public class CardDetailsService implements UserDetailsService {

	static final Logger logger = LoggerFactory.getLogger(CardDetailsService.class);

	@Autowired
	private CardService cardService;

	@Transactional(readOnly = true)
	public CardDetails loadCardByName(String name) throws UsernameNotFoundException {
		Card card = cardService.getCardsByName(name);
		logger.info("Card : {}", card);
		if (card == null) {
			logger.info("Card not found");
			throw new UsernameNotFoundException("Card not found");
		}
		return new org.springframework.security.core.userdetails.Card(card.getLogin(), card.getPassword(), true, true,
				true, true, getGrantedAuthorities(card));
	}

	private List<GrantedAuthority> getGrantedAuthorities(Card card) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		logger.info("Card profile : {}", card);
		authorities.add(new SimpleGrantedAuthority("ROLE_" + card.getRole()));
		logger.info("Authorities : {}", authorities);
		return authorities;
	}

}
