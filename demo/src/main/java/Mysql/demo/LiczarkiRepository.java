package Mysql.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LiczarkiRepository extends CrudRepository<Liczarki, Long> {

//    @Query(value = "SELECT c FROM liczarki12 WHERE c.typ LIKE '%' || :keyword || '%'"
//           + " OR c.state LIKE '%' || :keyword || '%'",nativeQuery=true)
    @Query(value = "SELECT * FROM liczarki12 c WHERE c.typ=:keyword OR c.state =:keyword"
          , nativeQuery=true)
   public List<Liczarki> search(@Param("keyword") String keyword);
}