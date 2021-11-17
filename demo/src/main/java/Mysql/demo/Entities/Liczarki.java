package Mysql.demo.Entities;

import org.hibernate.boot.registry.selector.StrategyRegistration;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@Entity

@Table(name = "Liczarki12")

public class Liczarki {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typ;
    private String state;

    public LocalDate getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    @Column (name = "DataPrzyjecia")
    private LocalDate dateStamp = LocalDate.now();
    @Column (name = "DataSprzedazy")
    private LocalDate sellDate;

    public void setDateStamp(LocalDate dateStamp) {
        this.dateStamp = dateStamp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    private String serial;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }



    @Override
    public String toString() {
        return "Liczarki{" +
                "id=" + id +
                ", typ='" + typ + '\'' +
                '}';
    }

    public LocalDate getDateStamp() {
        return dateStamp;
    }
}

