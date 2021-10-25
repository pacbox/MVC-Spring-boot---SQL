package Mysql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller

public class MainController {
//    @Autowired
//    public LiczarkiRepository liczarkiRepository;
    @Autowired
    public  LiczarkiService liczarkiService;

    @Autowired
    public StockService stockService;

    @Autowired
    public ServiceSellReport serviceSellReport;

    @Autowired
    SellReportRepository sellReportRepository;



    @Override
    public String toString() {
        return "MainController{" +
                "liczarkiService=" + liczarkiService +
                ", stockService=" + stockService +
                '}';
    }

    @GetMapping("")
    public String viewHomePage(Model model){
    List<Stock> list = stockService.checkStock();


       // model.addAttribute("gfs100", stockService.checkStock("gfs-100"));
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

    @GetMapping("/liczarki")
    public String listUsers(Model model) {
    List<Liczarki> listLiczarki= liczarkiService.findAll();
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

    @GetMapping("/stockStatus")
    public  String stockStatus(Model model){
        //Stock gfs100;
//        List <Stock> stockList = ;
        //model.addAttribute("gfs100", stockService.checkStock("gfs100"));
        model.addAttribute("gfs100", "100");
    return "index";}

//    @GetMapping("/choiceyear")
//    public String reportChoiceYear(Model model){
//        model.addAttribute("SellReport", new SellReport());
//
//        return "choiceyear";}

    @GetMapping("/choiceyear")
    public String reportChoiceYear(Model model){
        String year = null;
        String typ = null;
       model.addAttribute("year", year);
        model.addAttribute("typ", typ);
        return "choiceyear";}

    @PostMapping("/report")
    public String report(String year, String typ, Model model){
       // List<SellReport> sellReports = (List<SellReport>) sellReportRepository.findAll();
        List<SellReport> sellReports = serviceSellReport.createReport(typ,year);

        //Collections.sort(sellReports);
        Collections.reverse(sellReports);
        model.addAttribute("year", year);
        model.addAttribute("typ", typ);
        model.addAttribute("sellreports", sellReports);

        return "report";}


}
