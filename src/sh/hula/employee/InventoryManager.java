package sh.hula.employee;

public class InventoryManager {
    private java.util.Map<Integer, InventoryItem> inventory;

    public InventoryManager() {
        inventory = new java.util.HashMap<>();
    }

    // Přidání nové položky
    public void addItem(InventoryItem item) {
        if (inventory.containsKey(item.getId())) {
            System.out.println("CHYBA: Položka s ID " + item.getId() + " již existuje.");
            Logger.log("Pokus o přidání existující položky s ID: " + item.getId());
        } else {
            inventory.put(item.getId(), item);
            System.out.println("Položka přidána: " + item);
            Logger.log("Přidána nová položka s ID: " + item.getId());

            // Kontrola stavu zásob
            if (item.isBelowMinimum()) {
                System.out.println("UPOZORNĚNÍ: Položka " + item.getName() + " je pod minimálním stavem.");
                Logger.log("Položka s ID: " + item.getId() + " je pod minimálním stavem.");
            }
        }
    }

    // Aktualizace počtu kusů
    public void updateQuantity(int id, int newQuantity) {
        if (inventory.containsKey(id)) {
            InventoryItem item = inventory.get(id);
            item.setQuantity(newQuantity);
            System.out.println("Množství položky s ID " + id + " bylo aktualizováno na: " + newQuantity);
            Logger.log("Aktualizováno množství položky s ID: " + id + " na: " + newQuantity);

            // Kontrola stavu zásob
            if (item.isBelowMinimum()) {
                System.out.println("UPOZORNĚNÍ: Položka " + item.getName() + " je pod minimálním stavem.");
                Logger.log("Položka s ID: " + id + " je pod minimálním stavem.");
            }
        } else {
            System.out.println("CHYBA: Položka s ID " + id + " neexistuje.");
            Logger.log("Pokus o aktualizaci neexistující položky s ID: " + id);
        }
    }

    // Kontrola zásoby
    public boolean checkAvailability(int id, int requiredQuantity) {
        if (inventory.containsKey(id)) {
            InventoryItem item = inventory.get(id);
            return item.getQuantity() >= requiredQuantity;
        } else {
            System.out.println("CHYBA: Položka s ID " + id + " neexistuje.");
            Logger.log("Pokus o kontrolu dostupnosti neexistující položky s ID: " + id);
            return false;
        }
    }

    // Vyhledání položky podle ID
    public InventoryItem findItemById(int id) {
        if (inventory.containsKey(id)) {
            return inventory.get(id);
        } else {
            System.out.println("CHYBA: Položka s ID " + id + " neexistuje.");
            Logger.log("Pokus o nalezení neexistující položky s ID: " + id);
            return null;
        }
    }

    // Zobrazení všech položek pod minimálním stavem
    public void printLowStockItems() {
        System.out.println("Seznam položek pod minimálním stavem:");
        for (InventoryItem item : inventory.values()) {
            if (item.isBelowMinimum()) {
                System.out.println(item);
            }
        }
    }

    // Zobrazení všech položek
    public void printAllItems() {
        System.out.println("Seznam všech položek:");
        for (InventoryItem item : inventory.values()) {
            System.out.println(item);
        }
    }
}