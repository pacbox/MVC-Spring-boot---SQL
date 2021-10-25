package Mysql.demo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellReportRepository extends CrudRepository <SellReport, Long> {

//    @Query(value = "insert into sell_report (sell,typ,month,year)select count(id) as sell,typ,month(data_sprzedazy)"
//            + "as month, year(data_sprzedazy) from liczarki12  where state = 'sprzedano' and year(data_sprzedazy) = '2014'"
//            + "group by typ, month(data_sprzedazy), year(data_sprzedazy)", nativeQuery = true)
//    //public List<SellReport> checkSells(@Param("typ") String typ,@Param("year") String year);
//    public List<SellReport> checkSells();
    @Modifying
    @Query(value = "insert into sell_report (sell,typ,month,year) select count(id) as sell,typ,month(data_sprzedazy) as month, year(data_sprzedazy) from liczarki12  where state = 'sprzedano' and year(data_sprzedazy) = :year and typ = :typ group by typ, month(data_sprzedazy), year(data_sprzedazy)", nativeQuery = true)
    void check(@Param("typ") String typ,@Param("year") String year);

    @Modifying
    @Query(value="TRUNCATE TABLE sell_report", nativeQuery = true )
    void clear();

    @Query(value = "select * from sell_report", nativeQuery = true)
    List<SellReport> takeReport();
}
