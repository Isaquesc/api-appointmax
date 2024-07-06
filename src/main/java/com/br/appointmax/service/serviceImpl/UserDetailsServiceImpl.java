//package com.br.appointmax.service.serviceImpl;
//
//import com.br.appointmax.repository.UserRepository;
//import com.br.appointmax.model.Usuario;
//import com.br.appointmax.service.UserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Usuario usuario = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return User.withUsername(usuario.getUsername())
//                .password(usuario.getPassword())
//                .roles(usuario.getRoles())
//                .build();
//    }
//}