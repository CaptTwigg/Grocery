package Grocery;


import com.vaadin.annotations.Theme;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.dnd.DropEffect;
import com.vaadin.shared.ui.grid.DropMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.GridRowDragger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


@SpringUI
@Theme("valo")
public class MainUI extends UI  {

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    ShoppingInterface face = new ShoppingInterface();
    try {
      face.load();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


    HorizontalLayout layout = new HorizontalLayout();


    // create grids backed by list data providers
    Grid<Item> left = new Grid<>();
    left.setItems(face.getArray());
    left.addColumn(Item::getName)
    .setCaption("Item")
    ;

    left.addColumn(Item::getPrice)
    .setCaption("Price")
    ;

    left.addColumn(Item::getStock)
    .setCaption("Stock")
    ;

    Grid<Item> right = new Grid<>();
    right.setItems(new ArrayList<>());
    right.addColumn(Item::getName);

    left.setWidth(300, Sizeable.Unit.PIXELS);
    right.setWidth(300, Sizeable.Unit.PIXELS);

    // enable row dnd from left to right and handle drops
    GridRowDragger<Item> leftToRight = new GridRowDragger<>(left, right);

    // enable row dnd from right to left and handle drops
    GridRowDragger<Item> rightToLeft = new GridRowDragger<>(right, left);

    // don't allow drops to left when it is the source
    leftToRight.getGridDragSource()
      .addDragStartListener(event -> rightToLeft.getGridDropTarget()
        .setDropEffect(DropEffect.NONE));
    leftToRight.getGridDragSource().addDragEndListener(
      event -> rightToLeft.getGridDropTarget().setDropEffect(null));

    // don't allow drops to right when it is the source
    rightToLeft.getGridDragSource()
      .addDragStartListener(event -> leftToRight.getGridDropTarget()
        .setDropEffect(DropEffect.NONE));
    rightToLeft.getGridDragSource().addDragEndListener(
      event -> leftToRight.getGridDropTarget().setDropEffect(null));


    layout.addComponent(left);
    layout.addComponent(right);
    setContent(layout);
  }
}
