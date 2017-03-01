
package webproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/")
    public String index() {
        return "Testing";
    }
    
    @RequestMapping("/testi")
    public String testi(Model model) {
        model.addAttribute("message", "TESTI TESTI LEL");
        return "testi";
    }
}