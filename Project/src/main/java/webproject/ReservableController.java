
package webproject;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webproject.Models.Reservable;
import webproject.dataaccess.ReservableRepository;


@Controller
@RequestMapping("reservable")
public class ReservableController{

    private final ReservableRepository reservableRepository;
    
    public ReservableController(ReservableRepository reservableRepository){
        this.reservableRepository = reservableRepository;
    }
    
    @RequestMapping("")
    public String index(Model model, @PathVariable String id) {
        
        List<Reservable> res = reservableRepository.getAll();
        model.addAttribute("reservables", res);
        model.addAttribute("title", "Reservable");
        return "reservable/index";
    }
    
    @RequestMapping("/{id}")
    public String id(Model model, @PathVariable String id) {
        
        Reservable res = reservableRepository.get(Integer.parseInt(id));
        model.addAttribute("reservable", res);
        model.addAttribute("title", "Reservable");
        return "reservable/id";
    }
    
    @RequestMapping("/new")
    public String newReservable(){
        return "reservable/new";
    }
}
