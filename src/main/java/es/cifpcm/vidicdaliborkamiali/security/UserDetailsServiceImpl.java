package es.cifpcm.vidicdaliborkamiali.security;

import es.cifpcm.vidicdaliborkamiali.dao.UsersRepository;
import es.cifpcm.vidicdaliborkamiali.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UsersRepository usersRepository;
//
////    @Override
////    public UserDetails loadUserByUsername(String username)
////            throws UsernameNotFoundException {
////        UserDetails user = usersRepository.findUserByUsername(username);
////        return user;
////    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        User user = usersRepository.findAll().stream().filter(userFind->userFind.getUserName().equals(username)).findFirst().orElse(null);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return user;
//    }
//
//}
