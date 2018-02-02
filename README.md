## Brush-up Project: Grocery List
(in groups of 2 students) 

Write a class named GroceryList that represents a list of items to buy from the supermarket, and another class named GroceryItemOrder that represents a request to purchase a particular item in a given quantity (example: four boxes of cookies).

The GroceryList class should use an array to store the grocery item orders. Assume that a grocery list will have no more than 10 item orders. The GroceryList class should have an add-method that will add a given item order to the list if the list has fewer than 10 items, and a getTotalCost-method that will return the total sum cost of all grocery item orders in this list, printed out to the console (formatted nicely! containing name, quantity and total price). It should also have a toString()-method.

The GroceryItemOrder class should have an item name, a quantity, and a price per unit, all incapsulated, and it should have a constructor setting all these values, and one only setting the name(the default quantity should be one). It should have a getCost-method returning the total cost of the item in its given quantity, and a toString-method returning a String with the name, quantity, and total cost. All fields should have getter and setter methods.

Make a test class with a main-method that instantiates the GroceryList, fill it with GroceryItemOrder-objects, and print it. Test what happens if you try to add a grocery item order when the list is filled.
Instead of writing the names of the items when instantiating them in the test class, try to read them from a file. Make a .txt file with a number of grocery names in it, to test it.

Make a new version of GroceryList (name it GroceryList2). Change the implementation of the GroceryList2 to use an ArrayList instead of an array.

# If you finish fast: Make more improvements to your program!
