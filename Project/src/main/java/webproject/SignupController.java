

package webproject;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webproject.Models.User;
import webproject.dataaccess.UserRepository;

/**
 *
 * @author Arttu
 */

@Controller
public class SignupController {
    
    private final UserRepository userRepository;
    
    public SignupController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @RequestMapping("/signup")
    public String signup() {

        return "signup";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String createAccount(Model answer, HttpServletRequest request) {
        
        //Saadaan käyttäjän tiedot
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        
        //tarkistetaan onko username jo olemassa ja salasanan laatu.
        boolean acceptedAccountName = userRepository.usernameAvailable(username);
        
        
        if(acceptedAccountName && !password.equals("")) {
            answer.addAttribute("accountCreationSuccess", "Account created");
            User newUser = new User(username, password, name, "user");
            userRepository.save(newUser);
            return "login";
        }
        else {
            answer.addAttribute("accountCreationSuccess", "Account creation failed");
            return "signup";
        }
    }
}
