package top.felixu.entity;

/**
 * @Author felixu
 * @Date 2018.08.09
 */
public class Order {

    private String id;
    private double price;
    private int status;

    public Order(String id, double price, int status) {
        this.id = id;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
