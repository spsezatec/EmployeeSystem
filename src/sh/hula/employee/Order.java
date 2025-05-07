package sh.hula.employee;

public class Order {
    private int id;
    private String name;
    private String description;
    private OrderStatus status;
    private java.time.LocalDate receivedDate;
    private java.time.LocalDate dueDate;

    public Order(int id, String name, String description, OrderStatus status,
                 java.time.LocalDate receivedDate, java.time.LocalDate dueDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.receivedDate = receivedDate;
        this.dueDate = dueDate;
    }

    // Gettery a settery
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public java.time.LocalDate getReceivedDate() {
        return receivedDate;
    }

    public java.time.LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(java.time.LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Název: " + name + ", Popis: " + description +
                ", Stav: " + status.getText() + ", Přijato: " + receivedDate +
                ", Termín: " + dueDate;
    }
}