package Mysql.demo;

import Mysql.demo.Entities.Liczarki;
import Mysql.demo.Entities.SellReport;
import Mysql.demo.Repositories.SellReportRepository;
import Mysql.demo.Services.LiczarkiService;
import Mysql.demo.Services.ServiceSellReport;
import Mysql.demo.Services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller

public class MainController {
    @Autowired
    public LiczarkiService liczarkiService;

    @Autowired
    public StockService stockService;

    @Autowired
    public ServiceSellReport serviceSellReport;

    @Autowired
    public SellReportRepository sellReportRepository;

    @Override
    public String toString() {
        return "MainController{" +
                "liczarkiService=" + liczarkiService +
                ", stockService=" + stockService +
                '}';
    }

    @GetMapping("")
    public String viewHomePage(Model model) {
        List<Stock> list = stockService.checkStock();
        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/form")
    public String addNew(Model model){
        model.addAttribute("liczarki", new Liczarki());
        //System.out.println(model);
        return "form";
    }

    @PostMapping("/process_register")
    public String registration(Liczarki licz, RedirectAttributes re){
        liczarkiService.saveToRepo(licz);
        re.addFlashAttribute("message", "Urzadzenie dodane poprawnie");
        return "redirect:/form";
    }

    @PostMapping("/process_edit")
    public String processEdit(@Valid Liczarki licz, BindingResult result, RedirectAttributes re, Model model){
        if (result.hasErrors()){
            model.addAttribute("liczarki", licz);
            //String redirect = "redirect:/edit/"+licz.getId();
            return "edit";
        } else{
            liczarkiService.saveToRepo(licz);
            re.addFlashAttribute("message", "Urzadzenie zeedytowanie");
            String redirect = "redirect:/edit/"+licz.getId();
            return redirect;
        }

    }

    @GetMapping("/liczarki")
    public String listUsers(Model model) {
    List<Liczarki> listLiczarki= liczarkiService.findAll();
        model.addAttribute("listLiczarki", listLiczarki );
        return "liczarki";


        }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword,RedirectAttributes message) {
        ModelAndView mav = new ModelAndView("liczarki");
        ModelAndView mav2 = new ModelAndView("redirect:/");
        List<Liczarki> listLiczarki = liczarkiService.search(keyword);
        mav.addObject("listLiczarki", listLiczarki);
        if (listLiczarki.isEmpty()) {
           message.addFlashAttribute("message", "Brak wynik??w wyszukiwania dla: " + keyword);
           return mav2;
        }
        else {
            return mav;
        }
    }

@GetMapping("/edit/{id}")
public String edit(@PathVariable(value = "id") Long id, Model model) {
    Liczarki editP = liczarkiService.searchByID(id);
    model.addAttribute("liczarki", editP);
    return "edit";
}

    @GetMapping("/stockStatus")
    public String stockStatus(Model model) {
        model.addAttribute("gfs100", "100");
        return "index";
    }


    @GetMapping("/choiceyear")
    public String reportChoiceYear(Model model) {
        String year = null;
        String typ = null;
        model.addAttribute("year", year);
        model.addAttribute("typ", typ);
        return "choiceyear";
    }

    @PostMapping("/report")
    public String report(String year, String typ, Model model) {
        List<SellReport> sellReports = serviceSellReport.createReport(typ, year);
        Collections.reverse(sellReports);
        model.addAttribute("year", year);
        model.addAttribute("typ", typ);
        model.addAttribute("sellreports", sellReports);

        return "report";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value="id") Long id, RedirectAttributes mess){
        liczarkiService.deleteByID(id);
        mess.addFlashAttribute("message", "Urz??dzenie usuni??to ID: " + id + " !");
        return "redirect:/liczarki";
    }


}
