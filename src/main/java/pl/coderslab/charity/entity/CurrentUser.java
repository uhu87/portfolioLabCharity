package pl.coderslab.charity.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
public class CurrentUser extends User{

    private final pl.coderslab.charity.entity.User user;
   /* public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.charity.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }*/
  public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,  pl.coderslab.charity.entity.User user) {
      super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
      this.user = user;
  }
    public pl.coderslab.charity.entity.User  getUser() {return user;}

}
