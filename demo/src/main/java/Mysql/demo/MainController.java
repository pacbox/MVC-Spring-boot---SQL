package Mysql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Controller

public class MainController {
    @Autowired
    public LiczarkiRepository liczarkiRepository;

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/form")
    public String addNew(Model model){
        model.addAttribute("liczarki", new Liczarki());
        System.out.println(model);
        return "form";
    }

    @PostMapping("/process_register")
    public String registration(Liczarki licz){
       liczarkiRepository.save(licz);
       return "index";
    }

    @GetMapping("/liczarki")
    public String listUsers(Model model) {
        List<Liczarki> listLiczarki = (List<Liczarki>) liczarkiRepository.findAll();
        listLiczarki.forEach(System.out :: println);
        model.addAttribute("listLiczarki", listLiczarki);

        return "liczarki";
        //asdasd

    }

}
