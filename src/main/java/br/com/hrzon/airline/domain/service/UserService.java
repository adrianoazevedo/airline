package br.com.hrzon.airline.domain.service;

import br.com.hrzon.airline.infra.database.entity.User;
import br.com.hrzon.airline.infra.database.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByLogin(username);

    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException("Usuário não encontrado");
    }

    List<String> roles = new ArrayList<>();
    UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
            .username(user.getLogin())
            .password(user.getPassword())
            .roles(roles.toArray(new String[0]))
            .build();
    return userDetails;
  }

}
