
package webproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    /**
     * The main page
     * @param model
     * @return 
     */
    @RequestMapping("/")
    public String index(Model model) {
        
        model.addAttribute("userName", "Pekka");
        model.addAttribute("title", "Reservations");
        model.addAttribute("role", "admin");
        model.addAttribute("userId", "3");
        return "home";
    }
    
    //Tänne voisi esim laittaa
        //listan omista varauksista
        //linkit hallintopaneeleihin
        //lista varattavista kohteista, niistä linkit niiden varaussivulle
    
}