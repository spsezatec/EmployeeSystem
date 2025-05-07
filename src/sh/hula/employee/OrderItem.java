package sh.hula.employee;

public class OrderItem {
    private int inventoryItemId;
    private String name;
    private int quantity;

    public OrderItem(int inventoryItemId, String name, int quantity) {
        this.inventoryItemId = inventoryItemId;
        this.name = name;
        this.quantity = quantity;
    }

    public int getInventoryItemId() {
        return inventoryItemId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "ID: " + inventoryItemId + ", Název: " + name + ", Množství: " + quantity;
    }
}