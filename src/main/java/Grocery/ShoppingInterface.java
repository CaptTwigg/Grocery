package Grocery;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ShoppingInterface {

  private String file = "src/main/java/Grocery/ItemList.txt";
  private int totalItems = 0;

  private ArrayList<ShopItem> shoppingCart = new ArrayList<>();
  private ArrayList<Item> items = new ArrayList<>();


  public void menu() throws FileNotFoundException {
    load();
    addToBasket();
    totalCost();


  }

  public ArrayList<Item> getArray(){
    return items;
  }

  public void addToBasket() {
    Scanner input = new Scanner(System.in);

    for (Item i : items) {
      System.out.printf("Item: %-6s \t Price: %s \n", i.getName(), i.getPrice());

    }

    while (true) {

      if(totalItems >= 10) {
        System.out.println("Max amount of items. \nChecking out.");
        break;
      }

      System.out.println("Write the name of the items you want or enter 0 to checkout.");
      String itemNameInput = input.next();

      if (itemNameInput.equals("0"))
        break;

      boolean found = false;
      Item item = null;
      for (Item foundItem : items) {
        if (foundItem.getName().equalsIgnoreCase(itemNameInput)){
          item = foundItem;
          found = true;
        }

      }

        if (found) {
          int itemStock = item.getStock();


          int itemQuantityInput;
          while(true) {
            System.out.println("How many would you like? \nStock: " + item.getStock());
            itemQuantityInput = input.nextInt();
            if (itemQuantityInput > item.getStock() || itemQuantityInput > 10) {
              //continue re-do the loop from the start (do)
              System.out.println("Please choose an available amount.");
              continue;
            }
            break;

          }
          totalItems += itemQuantityInput;

          shoppingCart.add(new ShopItem.Builder()
            .item(item)
            .quantity(itemQuantityInput)
            .build());
        } else
          System.out.println("Item not found.");
      }

    }


  public void load() throws FileNotFoundException {
    Scanner scanFile = new Scanner(new File(file));
    scanFile.useLocale(Locale.ENGLISH);

    while (scanFile.hasNextLine()) {
      String itemName = scanFile.next();
      int itemStock = scanFile.nextInt();
      double itemCost = scanFile.nextDouble();

      items.add(new Item.Builder()
        .name(itemName)
        .stock(itemStock)
        .price(itemCost)
        .build()
      );
    }
  }

  private void totalCost(){
    double total = 0;
    for (ShopItem i : shoppingCart){
      total += i.getItem().getPrice() * i.getQuantity();
    }
    System.out.println("Total cost: " + total);

  }


}