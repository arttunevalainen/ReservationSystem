
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

        return "home";
    }
    
    //Tänne voisi esim laittaa
        //listan omista varauksista
        //linkit hallintopaneeleihin
        //lista varattavista kohteista, niistä linkit niiden varaussivulle
    
}