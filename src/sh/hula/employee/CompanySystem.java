package sh.hula.employee;

public class CompanySystem {
    private static CompanySystem instance;

    private EmployeeManager employeeManagement;
    private OrderManager orderManagement;
    private InventoryManager inventoryManagement;
    private OrderProcessing orderProcessing;

    private CompanySystem() {
        employeeManagement = new EmployeeManager();
        orderManagement = new OrderManager();
        inventoryManagement = new InventoryManager();
        orderProcessing = new OrderProcessing(inventoryManagement);

        System.out.println("Podnikový systém byl inicializován.");
        Logger.log("Inicializace podnikového systému");
    }

    public static CompanySystem getInstance() {
        if (instance == null) {
            instance = new CompanySystem();
        }
        return instance;
    }

    public EmployeeManager getEmployeeManagement() {
        return employeeManagement;
    }

    public OrderManager getOrderManagement() {
        return orderManagement;
    }

    public InventoryManager getInventoryManagement() {
        return inventoryManagement;
    }

    public OrderProcessing getOrderProcessing() {
        return orderProcessing;
    }
}