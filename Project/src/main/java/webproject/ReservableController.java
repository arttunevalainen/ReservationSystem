
package webproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webproject.dataaccess.ReservableRepository;


@Controller
@RequestMapping("reservable")
public class ReservableController{

    private final ReservableRepository reservableRepository;
    
    public ReservableController(ReservableRepository reservableRepository){
        this.reservableRepository = reservableRepository;
    }
    
    @RequestMapping("/{id}")
    public String index(Model model, @PathVariable String id) {
        
        
        
        model.addAttribute("title", "Reservable");
        return "reservable";
    }
}
