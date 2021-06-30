import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
  private final String name;
  private final String location;
  private final List<Item> menu = new ArrayList<Item>();
  public LocalTime openingTime;
  public LocalTime closingTime;

  public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
    this.name = name;
    this.location = location;
    this.openingTime = openingTime;
    this.closingTime = closingTime;
  }

  public boolean isRestaurantOpen() {
    LocalTime currentTime = getCurrentTime();
    return currentTime.isAfter(this.openingTime) && currentTime.isBefore(this.closingTime);
  }

  public LocalTime getCurrentTime() {
    return LocalTime.now();
  }

  public List<Item> getMenu() {
    return this.menu;
  }

  private Item findItemByName(String itemName) {
    for (Item item : menu) {
      if (item.getName().equals(itemName)) return item;
    }
    return null;
  }

  private int getItemPrice(String itemName) {
    for (Item item : menu) {
      if (item.getName().equals(itemName)) return item.getPrice();
    }
    return 0;
  }

  public void addToMenu(String name, int price) {
    Item newItem = new Item(name, price);
    menu.add(newItem);
  }

  public void removeFromMenu(String itemName) throws itemNotFoundException {
    Item itemToBeRemoved = findItemByName(itemName);
    if (itemToBeRemoved == null) throw new itemNotFoundException(itemName);
    menu.remove(itemToBeRemoved);
  }

  public int getOrderValue(List<String> orderItems) {
    /*
     * The name of the item returned when the user selects the item is always in
     *  the menu, hence a fail case scenario/Exception scenario would be unnecessary.
     */
    int orderValue = 0;
    for (String itemName : orderItems) {
      orderValue += getItemPrice(itemName);
    }
    System.out.println(
        "Total payable amount for the items- " + orderItems + " is " + orderValue + " Rupees.");
    return orderValue;
  }

  public void displayDetails() {
    System.out.println(
        "Restaurant:"
            + name
            + "\n"
            + "Location:"
            + location
            + "\n"
            + "Opening time:"
            + openingTime
            + "\n"
            + "Closing time:"
            + closingTime
            + "\n"
            + "Menu:"
            + "\n"
            + getMenu());
  }

  public String getName() {
    return name;
  }
}
