package Mysql.demo;

import org.hibernate.boot.registry.selector.StrategyRegistration;

import javax.persistence.*;

@Entity

@Table(name = "Liczarki12")

public class Liczarki {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typ;


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
}

