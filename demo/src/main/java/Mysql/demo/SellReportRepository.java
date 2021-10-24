package Mysql.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellReportRepository extends CrudRepository <SellReport, Long> {

    @Query(value = "insert into sell_report (sell,typ,month,year)select count(id) as sell,typ,month(data_sprzedazy)"
            + "as month, year(data_sprzedazy) from liczarki12  where state = 'sprzedano' and year(data_sprzedazy) = 2014"
            + "group by typ, month(data_sprzedazy), year(data_sprzedazy", nativeQuery = true)
    public List<SellReport> checkSells();

}
