package Mysql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    @Autowired
    LiczarkiRepository liczarkiRepository;

    @Override
    public String toString() {
        return "StockService{" +
                "liczarkiRepository=" + liczarkiRepository +
                '}';
    }

    public  List<Stock> checkStock(){
        Stock gfs100 = new Stock("gfs-100");
        gfs100.setCount(liczarkiRepository.checkStock(gfs100.getTyp()));
        Stock gfs120 = new Stock("gfs-120");
        gfs120.setCount(liczarkiRepository.checkStock(gfs120.getTyp()));
        List<Stock> stockList = new ArrayList<Stock>();
        stockList.add(gfs100);
        stockList.add(gfs120);



        //typ.setCount(liczarkiRepository.checkStock(typ.getTyp());
    return stockList;}

}
