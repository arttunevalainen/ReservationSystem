
package webproject;

//import javax.sql.DataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Päästetään tuntematon käyttäjä loginille.
        //Kirjautunut ohjautuu kotisivulle.
        http
            .authorizeRequests()
                .antMatchers("/login", "/signup", "/css/styles.css").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home", true)
                .and()
            .logout()
                .permitAll();
        
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        try {
            //Tähän tietokannasta tietojen otto.
            auth.jdbcAuthentication().dataSource(dataSource)
                    .usersByUsernameQuery(
                            "SELECT username, password, true FROM users WHERE username=?")
                    .authoritiesByUsernameQuery(
                            "SELECT username, role FROM users WHERE username=?");
            //auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
            }
        catch(Exception e) {
            
        }
    }
}