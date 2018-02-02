package Grocery;


import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.GridRowDragger;
import com.vaadin.ui.themes.ValoTheme;

import java.io.FileNotFoundException;
import java.util.ArrayList;


@SpringUI(path = "mike2")
@Theme("valo")
public class Mike2 extends UI {

  private ArrayList<Item> basket = new ArrayList<>();
  private ArrayList<Item> items = new ShoppingInterface().getArray();
  private Double totalCost = 0.0;
  private Label price = new Label();

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    price.setValue("Total cost: " + totalCost);

    GridLayout mainGrid = new GridLayout(16, 16);
    mainGrid.setSizeFull();
    mainGrid.addComponent(drag(), 1, 1);
    mainGrid.addComponent(price, 2, 1);


    setContent(mainGrid);

  }

  private HorizontalLayout drag() {

    HorizontalLayout layout = new HorizontalLayout();
    layout.setHeight(1000.0f, Unit.PERCENTAGE);

    // create grids backed by list data providers
    Grid<Item> right = new Grid<>();
    right.setSelectionMode(Grid.SelectionMode.NONE);
    right.setItems(basket);
    right.setHeight(layout.getHeight() / 10, Unit.PERCENTAGE);

    right.addColumn(Item::getName)
      .setCaption("Basket")
      .setResizable(false)
    ;

    Grid<Item> left = new Grid<>();
    left.setItems(items);
    left.setHeight(layout.getHeight() / 10, Unit.PERCENTAGE);
    left.setSelectionMode(Grid.SelectionMode.NONE);

    left.addColumn(Item::getName)
      .setCaption("Item")
      .setResizable(false)
      .setSortable(false)
    ;

    left.addColumn(Item::getPrice)
      .setCaption("Price")
      .setResizable(false)
      .setSortable(false)
    ;

    left.addColumn(Item::getStock)
      .setCaption("Stock")
      .setResizable(false)
      .setSortable(false)
    ;

    left.addComponentColumn(item -> button(item, right, left))
      .setCaption("Add/Remove")
      .setResizable(false)
      .setSortable(false)
      .setWidth(130)
    ;

    layout.addComponent(left);
    layout.addComponent(right);

    return layout;
  }

  private HorizontalLayout button(Item item, Grid grid, Grid left) {

    Button button = new Button(VaadinIcons.PLUS);
    button.addStyleName(ValoTheme.BUTTON_SMALL);
    grid.getDataProvider().refreshAll();

    Button button2 = new Button(VaadinIcons.MINUS);
    button2.addStyleName(ValoTheme.BUTTON_SMALL);
    grid.getDataProvider().refreshAll();

    update(item, button, button2, grid, left);

    HorizontalLayout layout = new HorizontalLayout();
    layout.setMargin(false);
    layout.setSpacing(false);
    layout.addComponent(button2);
    layout.addComponent(button);
    return layout;
  }

  private void update(Item item, Button buttonPlus, Button buttonMinus, Grid right, Grid left) {
    buttonPlus.addClickListener(event -> {
      if (item.getStock() > 0) {
        basket.add(item);
        totalCost += item.getPrice();
        item.setStock(item.getStock() - 1);
        right.getDataProvider().refreshAll();
        left.getDataProvider().refreshAll();
      }
    });

    buttonMinus.addClickListener(event -> {
      int index = getItemIndex(item);
      if (index != -1) {
        basket.remove(index);
        totalCost -= item.getPrice();
        item.setStock(item.getStock() + 1);
        right.getDataProvider().refreshAll();
        left.getDataProvider().refreshAll();
      }
    });
    price.setValue(String.format("Total cost: %(.2f", totalCost));

  }

  private int getItemIndex(Item item) {
    for (int i = 0; i < basket.size(); i++) {
      if (item.getName().equals(basket.get(i).getName()))
        return i;
    }
    return -1;
  }
}
