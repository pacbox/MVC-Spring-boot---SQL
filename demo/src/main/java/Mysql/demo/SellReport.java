package Mysql.demo;


import javax.persistence.*;

@Entity

//@SqlResultSetMapping(
//        name = "SellReportMapping",
//        entities = @EntityResult(
//                entityClass = SellReport.class,
//                fields = {
//                        @FieldResult(name = "id", column = "authorId"),
//                        @FieldResult(name = "firstName", column = "firstName"),
//                        @FieldResult(name = "lastName", column = "lastName"),
//                        @FieldResult(name = "version", column = "version")}))


@Table(name = "SellReport")
public class SellReport implements Comparable<SellReport> {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Long year;
    private Long month;
    private String typ;
    private Long sell;



    public SellReport() {

    }

    @Override
    public int compareTo(SellReport o) {
        return Double.compare(this.getMonth(),getMonth()); //implementacja comparable sortuje po miesiacach
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Long getSell() {
        return sell;
    }

    public void setSell(Long sell) {
        this.sell = sell;
    }

    @Override
    public String toString() {
        return "SellReport{" +
                "year=" + year +
                ", month=" + month +
                ", typ=" + typ +
                '}';
    }


}
