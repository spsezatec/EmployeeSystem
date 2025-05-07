package sh.hula.employee;

public class OrderManager {
    private java.util.Map<Integer, Order> orders;

    public OrderManager() {
        orders = new java.util.HashMap<>();
    }

    // Přidání nové zakázky
    public void addOrder(Order order) {
        if (orders.containsKey(order.getId())) {
            System.out.println("CHYBA: Zakázka s ID " + order.getId() + " již existuje.");
            Logger.log("Pokus o přidání existující zakázky s ID: " + order.getId());
        } else {
            orders.put(order.getId(), order);
            System.out.println("Zakázka přidána: " + order);
            Logger.log("Přidána nová zakázka s ID: " + order.getId());
        }
    }

    // Aktualizace stavu zakázky
    public void updateOrderStatus(int id, OrderStatus newStatus) {
        if (orders.containsKey(id)) {
            Order order = orders.get(id);
            order.setStatus(newStatus);
            System.out.println("Stav zakázky s ID " + id + " byl změněn na: " + newStatus.getText());
            Logger.log("Aktualizován stav zakázky s ID: " + id + " na: " + newStatus.getText());
        } else {
            System.out.println("CHYBA: Zakázka s ID " + id + " neexistuje.");
            Logger.log("Pokus o aktualizaci neexistující zakázky s ID: " + id);
        }
    }

    // Vyhledání zakázky podle ID
    public Order findOrderById(int id) {
        if (orders.containsKey(id)) {
            return orders.get(id);
        } else {
            System.out.println("CHYBA: Zakázka s ID " + id + " neexistuje.");
            Logger.log("Pokus o nalezení neexistující zakázky s ID: " + id);
            return null;
        }
    }

    // Zobrazení všech aktivních zakázek
    public void printActiveOrders() {
        System.out.println("Seznam aktivních zakázek:");
        for (Order order : orders.values()) {
            if (order.getStatus() == OrderStatus.PRIJATA || order.getStatus() == OrderStatus.PROBIHA) {
                System.out.println(order);
            }
        }
    }

    // Zobrazení všech zakázek
    public void printAllOrders() {
        System.out.println("Seznam všech zakázek:");
        for (Order order : orders.values()) {
            System.out.println(order);
        }
    }
}