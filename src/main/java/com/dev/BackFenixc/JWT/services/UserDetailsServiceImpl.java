package com.dev.BackFenixc.JWT.services;

import com.dev.BackFenixc.JWT.models.UserEntity;
import com.dev.BackFenixc.JWT.repositories.UserRepository;
import com.dev.BackFenixc.JWT.security.util.EmailUtil;
import com.dev.BackFenixc.JWT.security.util.OtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("No se encontro el usuario "+ username));

        Collection<?extends GrantedAuthority>authorities= userEntity.getRol()
                .stream()
                .map(rol->new SimpleGrantedAuthority("ROLE_".concat(rol.getRol().name())))
                .collect(Collectors.toSet());

        return new User(userEntity.getUsername(),
                userEntity.getPassword()
                        ,true
                        ,true
                        ,true
                        ,true
                        ,authorities);
    }
}
