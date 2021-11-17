package Mysql.demo.Repositories;

import Mysql.demo.Entities.Liczarki;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LiczarkiRepository extends CrudRepository<Liczarki, Long> {

//    @Query(value = "SELECT c FROM liczarki12 WHERE c.typ LIKE '%' || :keyword || '%'"
//           + " OR c.state LIKE '%' || :keyword || '%'",nativeQuery=true)
    @Query(value = "SELECT * FROM liczarki12 c WHERE c.typ=:keyword OR c.state =:keyword OR c.serial =:keyword"
          , nativeQuery=true)
   public List<Liczarki> search(@Param("keyword") String keyword);


    @Query(value= "SELECT COUNT(*) FROM liczarki12 WHERE state = 'Magazyn' and typ=:typ", nativeQuery=true)
    public int checkStock(@Param("typ") String typ);
}