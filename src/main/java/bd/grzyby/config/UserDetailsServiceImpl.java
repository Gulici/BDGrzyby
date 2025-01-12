package bd.grzyby.config;

import bd.grzyby.model.entity.Pracownik;
import bd.grzyby.repository.PracownikRepo;
import bd.grzyby.service.PracownikService;
import lombok.RequiredArgsConstructor;
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

    PracownikRepo pracownikRepo;

    public UserDetailsServiceImpl(PracownikRepo pracownikRepo) {
        this.pracownikRepo = pracownikRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pracownik pracownik = pracownikRepo.getPracownikByEmail(email);

        if (pracownik == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
        if(pracownik.getUprawnienia() == null || pracownik.getUprawnienia().isEmpty()) {
            throw new RuntimeException("Pracownik nie ma uprawnien");
        }
        Collection<GrantedAuthority> authorities = pracownik.getUprawnienia().stream()
                .map(uprawnienia -> new SimpleGrantedAuthority(uprawnienia.getNazwa()))
                .collect(Collectors.toList());

        return new User(pracownik.getEmail(), pracownik.getPassword(), authorities);
    }
}
