package uz.akfa.authorizationservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.akfa.authorizationservice.components.AuthUserDetail;
import uz.akfa.authorizationservice.entities.UserEntity;
import uz.akfa.authorizationservice.repositories.UserDetailRepository;

import java.util.Optional;


@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailServiceImpl  implements UserDetailsService {

    private final UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optional = userDetailRepository.findByUsernameIgnoreCase(username);
        if (optional.isEmpty())
            throw new UsernameNotFoundException("Username or password wrong");

        UserDetails userDetails = new AuthUserDetail(optional.get());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }
}
