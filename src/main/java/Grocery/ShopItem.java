package Grocery;

public class ShopItem {

  private Item item;
  private int quantity;

  public ShopItem() {
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return String.format("item: %s quantity: %s "
      , this.item, this.quantity);
  }


  public static final class Builder {

    private ShopItem shopItem;

    public Builder() {
      shopItem = new ShopItem();
    }

    public static Builder aShopItem() {
      return new Builder();
    }

    public Builder item(Item item) {
      shopItem.setItem(item);
      return this;
    }

    public Builder quantity(int quantity) {
      shopItem.setQuantity(quantity);
      return this;
    }

    public ShopItem build() {
      return shopItem;
    }
  }
}
