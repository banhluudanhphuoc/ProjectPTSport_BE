    package id.vn.ptsport.PTSport.Security;

    import id.vn.ptsport.PTSport.Entity.User;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.oauth2.core.user.OAuth2User;

    import java.util.Collection;
    import java.util.Collections;
    import java.util.List;
    import java.util.Map;

    @RequiredArgsConstructor
    public class UserPrincipal implements OAuth2User, UserDetails {
        private Integer id;
        private String userName;

        private String password;
        private Collection<? extends GrantedAuthority> authorities;
        private Map<String, Object> attributes;
        private OAuth2User oauth2User;


        public UserPrincipal(String userName, Collection<? extends GrantedAuthority> authorities) {
            this.userName = userName;
            this.authorities = authorities;
        }

        public UserPrincipal(String userName, String password, Collection<? extends GrantedAuthority> authorities) {
            this.userName = userName;
            this.password = password;
            this.authorities = authorities;
        }

        public static UserPrincipal create(User user, Collection<SimpleGrantedAuthority> authorities) {
            return new UserPrincipal(
                    user.getUsername(),
                    user.getPassword(),
                    authorities
            );
        }

        public static UserPrincipal create(User user) {
            List<GrantedAuthority> authorities = Collections.
                    singletonList(new SimpleGrantedAuthority("USER"));

            return new UserPrincipal(
                    user.getEmail(),
                    authorities
            );
        }

        public static UserPrincipal create(User user, Map<String, Object> attributes) {
            UserPrincipal userPrincipal = UserPrincipal.create(user);
            userPrincipal.setAttributes(attributes);
            return userPrincipal;
        }

        public Integer getId() {
            return id;
        }


        @Override
        public String getName() {
            return oauth2User.getAttribute("name");
        }

        public String getEmail() {
            return oauth2User.<String>getAttribute("email");
        }

        @Override
        public String getUsername() {
            return userName;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public Map<String, Object> getAttributes() {
            return attributes;
        }

        public void setAttributes(Map<String, Object> attributes) {
            this.attributes = attributes;
        }


    }
