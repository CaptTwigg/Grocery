package Grocery;

public class Item {

  //fields
  private String name;
  private int stock;
  private double price;

  //constructor
  public Item() {  }

  //getters and setters
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

  //overrides toString() method in java.lang.Object class
  @Override
  public String toString() {
    return String.format("name: %s stock: %s price: %s "
      , this.name, this.stock, this.price);
  }

  /**
   * Just like a constructor, the builder pattern
   * can can impose invariants on its parameters.
   *
   * You get client code, that's easier to read.
   */
  public static final class Builder {

    private Item item;

    public Builder() {
      item = new Item();
    }

    //not used
    /*public static Builder anItem() {
      return new Builder();
    }*/

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
