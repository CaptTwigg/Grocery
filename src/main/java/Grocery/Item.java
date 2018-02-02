package Grocery;

public class Item {
  private String name;
  private int stock;
  private double price;

  public Item() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return String.format("name: %s stock: %s price: %s "
      , this.name, this.stock, this.price);
  }


  public static final class Builder {

    private Item item;

    public Builder() {
      item = new Item();
    }

    public static Builder anItem() {
      return new Builder();
    }

    public Builder name(String name) {
      item.setName(name);
      return this;
    }

    public Builder stock(int stock) {
      item.setStock(stock);
      return this;
    }

    public Builder price(double price) {
      item.setPrice(price);
      return this;
    }

    public Item build() {
      return item;
    }
  }
}
