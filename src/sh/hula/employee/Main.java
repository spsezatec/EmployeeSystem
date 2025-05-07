package sh.hula.employee;
public class Main {
    private static java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static CompanySystem system = CompanySystem.getInstance();

    public static void main(String[] args) {
        System.out.println("=== PODNIKOVÝ SYSTÉM ===");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = readIntInput("Vyberte možnost: ");

            switch (choice) {
                case 1:
                    handleEmployeeManagement();
                    break;
                case 2:
                    handleOrderManagement();
                    break;
                case 3:
                    handleInventoryManagement();
                    break;
                case 4:
                    handleOrderProcessing();
                    break;
                case 5:
                    Logger.printLogs();
                    break;
                case 6:
                    loadDemoData();
                    System.out.println("Demonstrační data byla nahrána.");
                    break;
                case 0:
                    running = false;
                    System.out.println("Ukončení programu...");
                    break;
                default:
                    System.out.println("Neplatná volba. Zkuste to znovu.");
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n=== HLAVNÍ MENU ===");
        System.out.println("1. Správa zaměstnanců");
        System.out.println("2. Evidence zakázek");
        System.out.println("3. Správa zásob");
        System.out.println("4. Zpracování objednávek");
        System.out.println("5. Zobrazit logy systému");
        System.out.println("6. Nahrát demonstrační data");
        System.out.println("0. Ukončit program");
    }

    // SPRÁVA ZAMĚSTNANCŮ
    private static void handleEmployeeManagement() {
        EmployeeManager em = system.getEmployeeManagement();
        boolean submenuRunning = true;

        while (submenuRunning) {
            System.out.println("\n=== SPRÁVA ZAMĚSTNANCŮ ===");
            System.out.println("1. Přidat zaměstnance");
            System.out.println("2. Upravit zaměstnance");
            System.out.println("3. Odstranit zaměstnance");
            System.out.println("4. Vyhledat zaměstnance");
            System.out.println("5. Zobrazit všechny zaměstnance");
            System.out.println("6. Vypočítat celkové mzdové náklady");
            System.out.println("0. Zpět do hlavního menu");

            int choice = readIntInput("Vyberte možnost: ");

            switch (choice) {
                case 1:
                    addEmployee(em);
                    break;
                case 2:
                    updateEmployee(em);
                    break;
                case 3:
                    removeEmployee(em);
                    break;
                case 4:
                    findEmployee(em);
                    break;
                case 5:
                    em.printAllEmployees();
                    break;
                case 6:
                    em.calculateTotalSalaries();
                    break;
                case 0:
                    submenuRunning = false;
                    break;
                default:
                    System.out.println("Neplatná volba. Zkuste to znovu.");
            }
        }
    }

    private static void addEmployee(EmployeeManager em) {
        System.out.println("\n=== PŘIDÁNÍ ZAMĚSTNANCE ===");
        int id = readIntInput("Zadejte ID zaměstnance: ");
        System.out.print("Zadejte jméno: ");
        String firstName = scanner.nextLine();
        System.out.print("Zadejte příjmení: ");
        String lastName = scanner.nextLine();
        System.out.print("Zadejte pozici: ");
        String position = scanner.nextLine();
        double salary = readDoubleInput("Zadejte plat: ");

        Employee employee = new Employee(id, firstName, lastName, position, salary);
        em.addEmployee(employee);
    }

    private static void updateEmployee(EmployeeManager em) {
        System.out.println("\n=== ÚPRAVA ZAMĚSTNANCE ===");
        int id = readIntInput("Zadejte ID zaměstnance pro úpravu: ");
        Employee employee = em.findEmployeeById(id);

        if (employee != null) {
            System.out.println("Aktuální údaje: " + employee);
            System.out.print("Zadejte nové jméno (nebo Enter pro zachování stávajícího): ");
            String firstName = scanner.nextLine();
            if (firstName.isEmpty()) firstName = employee.getFirstName();

            System.out.print("Zadejte nové příjmení (nebo Enter pro zachování stávajícího): ");
            String lastName = scanner.nextLine();
            if (lastName.isEmpty()) lastName = employee.getLastName();

            System.out.print("Zadejte novou pozici (nebo Enter pro zachování stávající): ");
            String position = scanner.nextLine();
            if (position.isEmpty()) position = employee.getPosition();

            System.out.print("Zadejte nový plat (nebo 0 pro zachování stávajícího): ");
            String salaryStr = scanner.nextLine();
            double salary = salaryStr.isEmpty() ? employee.getSalary() : Double.parseDouble(salaryStr);

            em.updateEmployee(id, firstName, lastName, position, salary);
        }
    }

    private static void removeEmployee(EmployeeManager em) {
        System.out.println("\n=== ODSTRANĚNÍ ZAMĚSTNANCE ===");
        int id = readIntInput("Zadejte ID zaměstnance pro odstranění: ");
        em.removeEmployee(id);
    }

    private static void findEmployee(EmployeeManager em) {
        System.out.println("\n=== VYHLEDÁNÍ ZAMĚSTNANCE ===");
        int id = readIntInput("Zadejte ID zaměstnance: ");
        Employee employee = em.findEmployeeById(id);

        if (employee != null) {
            System.out.println("Nalezený zaměstnanec: " + employee);
        }
    }

    // EVIDENCE ZAKÁZEK
    private static void handleOrderManagement() {
        OrderManager om = system.getOrderManagement();
        boolean submenuRunning = true;

        while (submenuRunning) {
            System.out.println("\n=== EVIDENCE ZAKÁZEK ===");
            System.out.println("1. Přidat zakázku");
            System.out.println("2. Aktualizovat stav zakázky");
            System.out.println("3. Vyhledat zakázku");
            System.out.println("4. Zobrazit všechny zakázky");
            System.out.println("5. Zobrazit aktivní zakázky");
            System.out.println("0. Zpět do hlavního menu");

            int choice = readIntInput("Vyberte možnost: ");

            switch (choice) {
                case 1:
                    addOrder(om);
                    break;
                case 2:
                    updateOrderStatus(om);
                    break;
                case 3:
                    findOrder(om);
                    break;
                case 4:
                    om.printAllOrders();
                    break;
                case 5:
                    om.printActiveOrders();
                    break;
                case 0:
                    submenuRunning = false;
                    break;
                default:
                    System.out.println("Neplatná volba. Zkuste to znovu.");
            }
        }
    }

    private static void addOrder(OrderManager om) {
        System.out.println("\n=== PŘIDÁNÍ ZAKÁZKY ===");
        int id = readIntInput("Zadejte ID zakázky: ");
        System.out.print("Zadejte název zakázky: ");
        String name = scanner.nextLine();
        System.out.print("Zadejte popis zakázky: ");
        String description = scanner.nextLine();

        System.out.println("Vyberte status zakázky:");
        System.out.println("1. Přijata");
        System.out.println("2. Probíhá");
        System.out.println("3. Dokončena");
        System.out.println("4. Zrušena");
        int statusChoice = readIntInput("Vaše volba: ");

        OrderStatus status;
        switch (statusChoice) {
            case 1:
                status = OrderStatus.PRIJATA;
                break;
            case 2:
                status = OrderStatus.PROBIHA;
                break;
            case 3:
                status = OrderStatus.DOKONCENA;
                break;
            case 4:
                status = OrderStatus.ZRUSENA;
                break;
            default:
                System.out.println("Neplatná volba, nastavuji status na 'Přijata'.");
                status = OrderStatus.PRIJATA;
        }

        java.time.LocalDate receivedDate = java.time.LocalDate.now();

        System.out.print("Zadejte počet dní do termínu splnění: ");
        int days = Integer.parseInt(scanner.nextLine());
        java.time.LocalDate dueDate = receivedDate.plusDays(days);

        Order order = new Order(id, name, description, status, receivedDate, dueDate);
        om.addOrder(order);
    }

    private static void updateOrderStatus(OrderManager om) {
        System.out.println("\n=== AKTUALIZACE STAVU ZAKÁZKY ===");
        int id = readIntInput("Zadejte ID zakázky: ");
        Order order = om.findOrderById(id);

        if (order != null) {
            System.out.println("Aktuální stav zakázky: " + order.getStatus().getText());
            System.out.println("Vyberte nový status zakázky:");
            System.out.println("1. Přijata");
            System.out.println("2. Probíhá");
            System.out.println("3. Dokončena");
            System.out.println("4. Zrušena");
            int statusChoice = readIntInput("Vaše volba: ");

            OrderStatus newStatus;
            switch (statusChoice) {
                case 1:
                    newStatus = OrderStatus.PRIJATA;
                    break;
                case 2:
                    newStatus = OrderStatus.PROBIHA;
                    break;
                case 3:
                    newStatus = OrderStatus.DOKONCENA;
                    break;
                case 4:
                    newStatus = OrderStatus.ZRUSENA;
                    break;
                default:
                    System.out.println("Neplatná volba, stav nebyl změněn.");
                    return;
            }

            om.updateOrderStatus(id, newStatus);
        }
    }

    private static void findOrder(OrderManager om) {
        System.out.println("\n=== VYHLEDÁNÍ ZAKÁZKY ===");
        int id = readIntInput("Zadejte ID zakázky: ");
        Order order = om.findOrderById(id);

        if (order != null) {
            System.out.println("Nalezená zakázka: " + order);
        }
    }

    // SPRÁVA ZÁSOB
    private static void handleInventoryManagement() {
        InventoryManager im = system.getInventoryManagement();
        boolean submenuRunning = true;

        while (submenuRunning) {
            System.out.println("\n=== SPRÁVA ZÁSOB ===");
            System.out.println("1. Přidat položku do inventáře");
            System.out.println("2. Aktualizovat množství položky");
            System.out.println("3. Vyhledat položku");
            System.out.println("4. Zobrazit všechny položky");
            System.out.println("5. Zobrazit položky pod minimálním stavem");
            System.out.println("0. Zpět do hlavního menu");

            int choice = readIntInput("Vyberte možnost: ");

            switch (choice) {
                case 1:
                    addInventoryItem(im);
                    break;
                case 2:
                    updateInventoryItemQuantity(im);
                    break;
                case 3:
                    findInventoryItem(im);
                    break;
                case 4:
                    im.printAllItems();
                    break;
                case 5:
                    im.printLowStockItems();
                    break;
                case 0:
                    submenuRunning = false;
                    break;
                default:
                    System.out.println("Neplatná volba. Zkuste to znovu.");
            }
        }
    }

    private static void addInventoryItem(InventoryManager im) {
        System.out.println("\n=== PŘIDÁNÍ POLOŽKY DO INVENTÁŘE ===");
        int id = readIntInput("Zadejte ID položky: ");
        System.out.print("Zadejte název položky: ");
        String name = scanner.nextLine();
        int quantity = readIntInput("Zadejte počet kusů: ");
        int minQuantity = readIntInput("Zadejte minimální požadovaný stav: ");

        InventoryItem item = new InventoryItem(id, name, quantity, minQuantity);
        im.addItem(item);
    }

    private static void updateInventoryItemQuantity(InventoryManager im) {
        System.out.println("\n=== AKTUALIZACE MNOŽSTVÍ POLOŽKY ===");
        int id = readIntInput("Zadejte ID položky: ");
        InventoryItem item = im.findItemById(id);

        if (item != null) {
            System.out.println("Aktuální stav položky: " + item.getQuantity() + " ks");
            int newQuantity = readIntInput("Zadejte nové množství: ");
            im.updateQuantity(id, newQuantity);
        }
    }

    private static void findInventoryItem(InventoryManager im) {
        System.out.println("\n=== VYHLEDÁNÍ POLOŽKY ===");
        int id = readIntInput("Zadejte ID položky: ");
        InventoryItem item = im.findItemById(id);

        if (item != null) {
            System.out.println("Nalezená položka: " + item);
        }
    }

    // ZPRACOVÁNÍ OBJEDNÁVEK
    private static void handleOrderProcessing() {
        OrderProcessing op = system.getOrderProcessing();
        InventoryManager im = system.getInventoryManagement();
        boolean submenuRunning = true;

        while (submenuRunning) {
            System.out.println("\n=== ZPRACOVÁNÍ OBJEDNÁVEK ===");
            System.out.println("1. Vytvořit novou objednávku");
            System.out.println("2. Přidat položku do objednávky");
            System.out.println("3. Zpracovat objednávku");
            System.out.println("4. Vyhledat objednávku");
            System.out.println("5. Zobrazit všechny objednávky");
            System.out.println("0. Zpět do hlavního menu");

            int choice = readIntInput("Vyberte možnost: ");

            switch (choice) {
                case 1:
                    createCustomerOrder(op);
                    break;
                case 2:
                    addItemToCustomerOrder(op, im);
                    break;
                case 3:
                    processCustomerOrder(op);
                    break;
                case 4:
                    findCustomerOrder(op);
                    break;
                case 5:
                    op.printAllOrders();
                    break;
                case 0:
                    submenuRunning = false;
                    break;
                default:
                    System.out.println("Neplatná volba. Zkuste to znovu.");
            }
        }
    }

    private static void createCustomerOrder(OrderProcessing op) {
        System.out.println("\n=== VYTVOŘENÍ NOVÉ OBJEDNÁVKY ===");
        int id = readIntInput("Zadejte ID objednávky: ");
        System.out.print("Zadejte jméno zákazníka: ");
        String customerName = scanner.nextLine();

        op.createOrder(id, customerName);
    }

    private static void addItemToCustomerOrder(OrderProcessing op, InventoryManager im) {
        System.out.println("\n=== PŘIDÁNÍ POLOŽKY DO OBJEDNÁVKY ===");
        int orderId = readIntInput("Zadejte ID objednávky: ");
        CustomerOrder order = op.findOrderById(orderId);

        if (order != null && !order.isProcessed()) {
            // Zobrazení dostupných položek v inventáři
            im.printAllItems();

            int itemId = readIntInput("Zadejte ID položky z inventáře: ");
            InventoryItem item = im.findItemById(itemId);

            if (item != null) {
                int quantity = readIntInput("Zadejte požadované množství: ");

                if (quantity > 0) {
                    op.addItemToOrder(orderId, itemId, item.getName(), quantity);
                } else {
                    System.out.println("Množství musí být větší než 0.");
                }
            }
        } else if (order != null && order.isProcessed()) {
            System.out.println("Objednávka již byla zpracována a nelze ji upravovat.");
        }
    }

    private static void processCustomerOrder(OrderProcessing op) {
        System.out.println("\n=== ZPRACOVÁNÍ OBJEDNÁVKY ===");
        int orderId = readIntInput("Zadejte ID objednávky ke zpracování: ");
        op.processOrder(orderId);
    }

    private static void findCustomerOrder(OrderProcessing op) {
        System.out.println("\n=== VYHLEDÁNÍ OBJEDNÁVKY ===");
        int orderId = readIntInput("Zadejte ID objednávky: ");
        CustomerOrder order = op.findOrderById(orderId);

        if (order != null) {
            System.out.println("Nalezená objednávka: " + order);
        }
    }

    // Pomocné metody pro načítání vstupů
    private static int readIntInput(String prompt) {
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                value = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Neplatný vstup. Zadejte celé číslo.");
            }
        }

        return value;
    }

    private static double readDoubleInput(String prompt) {
        double value = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                value = Double.parseDouble(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Neplatný vstup. Zadejte číslo.");
            }
        }

        return value;
    }

    // Metoda pro nahrání demonstračních dat
    private static void loadDemoData() {
        // Zaměstnanci
        EmployeeManager em = system.getEmployeeManagement();
        em.addEmployee(new Employee(1, "Jan", "Novák", "Programátor", 45000));
        em.addEmployee(new Employee(2, "Petr", "Svoboda", "Tester", 35000));
        em.addEmployee(new Employee(3, "Marie", "Černá", "Manažer", 60000));

        // Zakázky
        OrderManager om = system.getOrderManagement();
        om.addOrder(new Order(1, "Webové stránky", "Vytvoření firemních stránek",
                OrderStatus.PRIJATA, java.time.LocalDate.now(),
                java.time.LocalDate.now().plusDays(30)));
        om.addOrder(new Order(2, "E-Shop", "Implementace e-shopu",
                OrderStatus.PROBIHA, java.time.LocalDate.now().minusDays(10),
                java.time.LocalDate.now().plusDays(20)));
        om.addOrder(new Order(3, "Oprava serveru", "Výměna disků a údržba",
                OrderStatus.DOKONCENA, java.time.LocalDate.now().minusDays(20),
                java.time.LocalDate.now().minusDays(15)));

        // Zásoby
        InventoryManager im = system.getInventoryManagement();
        im.addItem(new InventoryItem(1, "Procesor", 20, 10));
        im.addItem(new InventoryItem(2, "RAM", 50, 20));
        im.addItem(new InventoryItem(3, "SSD", 15, 15));
        im.addItem(new InventoryItem(4, "Grafická karta", 5, 10));

        // Objednávky
        OrderProcessing op = system.getOrderProcessing();
        CustomerOrder co1 = op.createOrder(1, "Firma XYZ");
        op.addItemToOrder(1, 1, "Procesor", 5);
        op.addItemToOrder(1, 2, "RAM", 10);
    }
}