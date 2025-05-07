package sh.hula.employee;

public class CustomerOrder {
    private int id;
    private String customerName;
    private java.util.List<OrderItem> items;
    private boolean processed;
    private java.time.LocalDate orderDate;

    public CustomerOrder(int id, String customerName, java.time.LocalDate orderDate) {
        this.id = id;
        this.customerName = customerName;
        this.items = new java.util.ArrayList<>();
        this.processed = false;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public java.util.List<OrderItem> getItems() {
        return items;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public java.time.LocalDate getOrderDate() {
        return orderDate;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Objednávka ID: ").append(id)
                .append(", Zákazník: ").append(customerName)
                .append(", Datum: ").append(orderDate)
                .append(", Zpracováno: ").append(processed ? "Ano" : "Ne")
                .append("\nPoložky:");

        for (OrderItem item : items) {
            sb.append("\n  ").append(item);
        }
        return sb.toString();
    }
}