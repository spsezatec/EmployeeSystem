package sh.hula.employee;

public enum OrderStatus {
    PRIJATA("Přijata"),
    PROBIHA("Probíhá"),
    DOKONCENA("Dokončena"),
    ZRUSENA("Zrušena");

    private final String text;

    OrderStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}