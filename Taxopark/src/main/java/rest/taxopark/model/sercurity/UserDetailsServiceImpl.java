package rest.taxopark.model.sercurity;

import antlr.debug.SemanticPredicateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rest.taxopark.model.entites.User;
import rest.taxopark.model.repository.UserRepository;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String emial) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(emial).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));

        return SecurityUser.fromUser(user);
    }
}
