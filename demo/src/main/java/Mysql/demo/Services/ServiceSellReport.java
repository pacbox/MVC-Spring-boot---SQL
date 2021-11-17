package Mysql.demo.Services;

import Mysql.demo.Entities.SellReport;
import Mysql.demo.Repositories.SellReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;



@Service
public class ServiceSellReport {

   @Autowired
   SellReportRepository sellReportRepository;


   @Transactional
public List<SellReport> createReport(String typ, String year){
    sellReportRepository.clear();
    sellReportRepository.check(typ, year);

    return sellReportRepository.takeReport();}
}

