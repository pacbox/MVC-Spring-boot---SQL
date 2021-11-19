package Mysql.demo;

public class Stock {

    private String typ;
    private int count;

    public Stock(String typ){
        this.typ = typ;
        }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "typ='" + typ + '\'' +
                ", count=" + count +
                '}';
    }
}
