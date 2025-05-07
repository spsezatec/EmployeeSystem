package sh.hula.employee;

public class EmployeeManager {
    private java.util.Map<Integer, Employee> employees;

    public EmployeeManager() {
        employees = new java.util.HashMap<>();
    }

    // Přidání zaměstnance
    public void addEmployee(Employee employee) {
        if (employees.containsKey(employee.getId())) {
            System.out.println("CHYBA: Zaměstnanec s ID " + employee.getId() + " již existuje.");
            Logger.log("Pokus o přidání existujícího zaměstnance s ID: " + employee.getId());
        } else {
            employees.put(employee.getId(), employee);
            System.out.println("Zaměstnanec přidán: " + employee);
            Logger.log("Přidán nový zaměstnanec s ID: " + employee.getId());
        }
    }

    // Editace zaměstnance
    public void updateEmployee(int id, String firstName, String lastName, String position, double salary) {
        if (employees.containsKey(id)) {
            Employee employee = employees.get(id);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setPosition(position);
            employee.setSalary(salary);
            System.out.println("Zaměstnanec aktualizován: " + employee);
            Logger.log("Aktualizován zaměstnanec s ID: " + id);
        } else {
            System.out.println("CHYBA: Zaměstnanec s ID " + id + " neexistuje.");
            Logger.log("Pokus o aktualizaci neexistujícího zaměstnance s ID: " + id);
        }
    }

    // Odstranění zaměstnance
    public void removeEmployee(int id) {
        if (employees.containsKey(id)) {
            employees.remove(id);
            System.out.println("Zaměstnanec s ID " + id + " byl odstraněn.");
            Logger.log("Odstraněn zaměstnanec s ID: " + id);
        } else {
            System.out.println("CHYBA: Zaměstnanec s ID " + id + " neexistuje.");
            Logger.log("Pokus o odstranění neexistujícího zaměstnance s ID: " + id);
        }
    }

    // Vyhledání zaměstnance podle ID
    public Employee findEmployeeById(int id) {
        if (employees.containsKey(id)) {
            return employees.get(id);
        } else {
            System.out.println("CHYBA: Zaměstnanec s ID " + id + " neexistuje.");
            Logger.log("Pokus o nalezení neexistujícího zaměstnance s ID: " + id);
            return null;
        }
    }

    // Výpis všech zaměstnanců
    public void printAllEmployees() {
        System.out.println("Seznam všech zaměstnanců:");
        for (Employee employee : employees.values()) {
            System.out.println(employee);
        }
    }

    // Výpočet celkových mzdových nákladů
    public double calculateTotalSalaries() {
        double totalSalaries = 0;
        for (Employee employee : employees.values()) {
            totalSalaries += employee.getSalary();
        }
        System.out.println("Celkové mzdové náklady: " + totalSalaries + " Kč");
        return totalSalaries;
    }
}