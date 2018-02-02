package Grocery;


import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.dnd.DropEffect;
import com.vaadin.shared.ui.grid.DropMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.GridRowDragger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


@SpringUI(path = "mike")
@Theme("valo")
public class Mike extends UI {

  @Override
  protected void init(VaadinRequest vaadinRequest) {

    GridLayout mainGrid = new GridLayout(16, 16);
    mainGrid.setSizeFull();
    mainGrid.addComponent(drag(), 1, 1);


    setContent(mainGrid);

  }

  private HorizontalLayout drag() {
    ShoppingInterface face = new ShoppingInterface();
    try {
      face.load();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


    HorizontalLayout layout = new HorizontalLayout();
    layout.setHeight(1000.0f, Unit.PERCENTAGE);

    Button button = new Button(VaadinIcons.PLUS);
    // create grids backed by list data providers
    Grid<Item> left = new Grid<>();
    left.setItems(face.getArray());
    left.setHeight(layout.getHeight() / 10, Unit.PERCENTAGE);

    left.addColumn(Item::getName)
      .setCaption("Item")
      .setResizable(false)
    ;

    left.addColumn(Item::getPrice)
      .setCaption("Price")
      .setResizable(false)
    ;

    left.addColumn(Item::getStock)
      .setCaption("Stock")
      .setResizable(false)
    ;


    Grid<Item> right = new Grid<>();
    right.setItems(new ArrayList<>());
    right.setHeight(layout.getHeight() / 10, Unit.PERCENTAGE);

    right.addColumn(Item::getName)
      .setCaption("Basket")
      .setResizable(false)
    ;

    left.setWidth(300, Sizeable.Unit.PIXELS);
    right.setWidth(300, Sizeable.Unit.PIXELS);

    // enable row dnd from left to right and handle drops
    GridRowDragger<Item> leftToRight = new GridRowDragger<>(left, right);

    // enable row dnd from right to left and handle drops
    GridRowDragger<Item> rightToLeft = new GridRowDragger<>(right, left);




    layout.addComponent(left);
    layout.addComponent(right);

    return layout;
  }
}
