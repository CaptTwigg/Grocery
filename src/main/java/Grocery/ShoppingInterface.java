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

  //metode vi bruger til at hente ind i vores UI
  public ArrayList<Item> getArray() {
    try {
      load();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return items;
  }

  private void addToBasket() {
    Scanner input = new Scanner(System.in);


    while (true) {

      for (Item i : items) System.out.printf("Item: %-6s \t Price: %s\n", i.getName(), i.getPrice());

      if (totalItems >= 10) {
        System.out.println("Max amount of items. \nChecking out.");
        break;
      }

      System.out.println("Write the name of the items you want or enter 0 to checkout.\n");
      String itemNameInput = input.next();

      if (itemNameInput.equals("0"))
        break;

      boolean found = false;
      Item item = null;
      for (Item foundItem : items) {
        if (foundItem.getName().equalsIgnoreCase(itemNameInput)) {
          item = foundItem;
          found = true;
        }

      }

      if (found) {
        //object initialized for further use in two scopes
        int itemQuantityInput;

        while (true) {
          System.out.println("How many would you like? Choose from 1-10.\nStock: " + item.getStock());
          itemQuantityInput = input.nextInt();

          if (itemQuantityInput > item.getStock() || itemQuantityInput > 10 || itemQuantityInput <= 0) {
            //continue re-do the loop from the start (do)
            System.out.println("Please choose an available amount.");
            continue;
          }
          break;
        }
        totalItems += itemQuantityInput;

        //client code from the builder pattern. Easier to read if there's more details.
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
    scanFile.useLocale(Locale.ENGLISH); //read the delimiter

    while (scanFile.hasNextLine()) {
      items.add(new Item.Builder()
        .name(scanFile.next())
        .stock(scanFile.nextInt())
        .price(scanFile.nextDouble())
        .build()
      );
    }
  }

  private void totalCost() {
    double total = 0;
    for (ShopItem i : shoppingCart) {
      total += i.getItem().getPrice() * i.getQuantity();
    }
    System.out.printf("Total cost: %.2f Bitcoins", total);
  }
}
