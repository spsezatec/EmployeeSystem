package sh.hula.employee;

public class Logger {
    private static java.util.List<String> logs = new java.util.ArrayList<>();

    public static void log(String message) {
        String timestamp = java.time.LocalDateTime.now().toString();
        String logEntry = "[" + timestamp + "] " + message;
        logs.add(logEntry);

        // Zde by mohlo být zapisování do souboru
    }

    public static void printLogs() {
        System.out.println("Výpis logu:");
        for (String log : logs) {
            System.out.println(log);
        }
    }
}