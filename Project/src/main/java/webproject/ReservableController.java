
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
    
    @RequestMapping("/list")
    public String index(Model model) {
        //tänne lista reservableista ja nappi uuden lisäämiseksi
        List<Reservable> res = reservableRepository.getAll();
        model.addAttribute("reservables", res);
        model.addAttribute("title", "Reservable");
        return "reservable/index";
    }
    
    @RequestMapping("/info/{id}")
    public String id(Model model, @PathVariable String id) {
        //tsekkaa että userilla oikeudet nähdä tämä
        Reservable res = reservableRepository.get(Integer.parseInt(id));
        model.addAttribute("reservable", res);
        model.addAttribute("title", "Reservable");
        return "reservable/id";
    }
    
    @RequestMapping("/new")
    public String newReservable(Model model){
        //tsekkaa että oikeus lisätä reservable
        return "reservable/new";
    }
}
