package pl.pollub.cs.pentagoncafe.flare.DTO.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.AuthUserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Role;

import java.util.Collection;
import java.util.Collections;

@Data
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private String email;
    private Role role;
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean enabled = true;

    public UserDetailsImpl(String username, String password, String email, boolean enabled, boolean accountNonLocked, Role role) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setEnabled(enabled);
        this.setAccountNonLocked(accountNonLocked);
        this.role = role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

    public AuthUserResponseDTO getUserResponseDTO(){
        return new AuthUserResponseDTO(this.username,this.email,this.role.name());
    }
}
