package Mysql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class MainController {
//    @Autowired
//    public LiczarkiRepository liczarkiRepository;
    @Autowired
    public  LiczarkiService liczarkiService;

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
        liczarkiService.saveToRepo(licz);
        return "index";
    }

    @GetMapping("/liczarki")
    public String listUsers(Model model) {
    List<Liczarki> listLiczarki= liczarkiService.findAll();
       // listLiczarki.forEach(System.out :: println);
        model.addAttribute("listLiczarki", listLiczarki );
        return "liczarki";


        }
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword){
        //System.out.println(keyword);
        ModelAndView mav = new ModelAndView("liczarki");
       List <Liczarki> listLiczarki = liczarkiService.search(keyword);
        mav.addObject("listLiczarki",listLiczarki );
        System.out.println(listLiczarki);
  //      mav.addObject("message",keyword);
        return mav;
    }
//    @GetMapping("/edit/{id}")
//    public ModelAndView edit(@PathVariable (value="id")  Long id){
//        System.out.println(id);
//     ModelAndView mav = new ModelAndView("edit");
//     Liczarki editP = liczarkiService.searchByID(id);
//     mav.addObject("liczarki",editP);
//     System.out.println(editP);
//     return mav;
//    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable (value="id")  Long id, Model model){
        System.out.println(id);

     Liczarki editP = liczarkiService.searchByID(id);
     model.addAttribute("liczarki", editP);
     System.out.println(editP);
     return "edit";
    }

}
