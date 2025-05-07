package sh.hula.employee;

public class OrderProcessing {
    private java.util.Map<Integer, CustomerOrder> orders;
    private InventoryManager inventoryManagement;

    public OrderProcessing(InventoryManager inventoryManagement) {
        this.orders = new java.util.HashMap<>();
        this.inventoryManagement = inventoryManagement;
    }

    // Vytvoření nové objednávky
    public CustomerOrder createOrder(int id, String customerName) {
        if (orders.containsKey(id)) {
            System.out.println("CHYBA: Objednávka s ID " + id + " již existuje.");
            Logger.log("Pokus o vytvoření existující objednávky s ID: " + id);
            return null;
        } else {
            CustomerOrder order = new CustomerOrder(id, customerName, java.time.LocalDate.now());
            orders.put(id, order);
            System.out.println("Objednávka vytvořena: " + order);
            Logger.log("Vytvořena nová objednávka s ID: " + id);
            return order;
        }
    }

    // Přidání položky do objednávky
    public void addItemToOrder(int orderId, int inventoryItemId, String name, int quantity) {
        if (orders.containsKey(orderId)) {
            CustomerOrder order = orders.get(orderId);
            if (!order.isProcessed()) {
                OrderItem item = new OrderItem(inventoryItemId, name, quantity);
                order.addItem(item);
                System.out.println("Položka přidána do objednávky " + orderId + ": " + item);
                Logger.log("Přidána položka do objednávky s ID: " + orderId);
            } else {
                System.out.println("CHYBA: Objednávka s ID " + orderId + " již byla zpracována.");
                Logger.log("Pokus o úpravu již zpracované objednávky s ID: " + orderId);
            }
        } else {
            System.out.println("CHYBA: Objednávka s ID " + orderId + " neexistuje.");
            Logger.log("Pokus o úpravu neexistující objednávky s ID: " + orderId);
        }
    }

    // Zpracování objednávky
    public void processOrder(int orderId) {
        if (orders.containsKey(orderId)) {
            CustomerOrder order = orders.get(orderId);
            if (!order.isProcessed()) {
                boolean canBeProcessed = true;

                // Kontrola dostupnosti všech položek
                for (OrderItem item : order.getItems()) {
                    if (!inventoryManagement.checkAvailability(item.getInventoryItemId(), item.getQuantity())) {
                        InventoryItem invItem = inventoryManagement.findItemById(item.getInventoryItemId());
                        int available = invItem != null ? invItem.getQuantity() : 0;
                        System.out.println("CHYBA: Nedostatek zásob pro položku " + item.getName() +
                                ". Požadováno: " + item.getQuantity() + ", Dostupno: " + available);
                        Logger.log("Nedostatek zásob pro položku s ID: " + item.getInventoryItemId() +
                                " v objednávce " + orderId);
                        canBeProcessed = false;
                    }
                }

                if (canBeProcessed) {
                    // Aktualizace zásob
                    for (OrderItem item : order.getItems()) {
                        InventoryItem invItem = inventoryManagement.findItemById(item.getInventoryItemId());
                        inventoryManagement.updateQuantity(item.getInventoryItemId(),
                                invItem.getQuantity() - item.getQuantity());
                    }

                    order.setProcessed(true);
                    System.out.println("Objednávka s ID " + orderId + " byla úspěšně zpracována.");
                    Logger.log("Zpracována objednávka s ID: " + orderId);
                } else {
                    System.out.println("Objednávka s ID " + orderId + " nemohla být zpracována kvůli nedostatku zásob.");
                    Logger.log("Objednávka s ID: " + orderId + " nebyla zpracována kvůli nedostatku zásob");
                }
            } else {
                System.out.println("CHYBA: Objednávka s ID " + orderId + " již byla zpracována.");
                Logger.log("Pokus o opětovné zpracování objednávky s ID: " + orderId);
            }
        } else {
            System.out.println("CHYBA: Objednávka s ID " + orderId + " neexistuje.");
            Logger.log("Pokus o zpracování neexistující objednávky s ID: " + orderId);
        }
    }

    // Vyhledání objednávky podle ID
    public CustomerOrder findOrderById(int id) {
        if (orders.containsKey(id)) {
            return orders.get(id);
        } else {
            System.out.println("CHYBA: Objednávka s ID " + id + " neexistuje.");
            Logger.log("Pokus o nalezení neexistující objednávky s ID: " + id);
            return null;
        }
    }

    // Zobrazení všech objednávek
    public void printAllOrders() {
        System.out.println("Seznam všech objednávek:");
        for (CustomerOrder order : orders.values()) {
            System.out.println(order);
        }
    }
}