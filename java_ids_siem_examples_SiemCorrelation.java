import java.util.List;

public class SiemCorrelation {
    public static void main(String[] args) {
        // Сырые логи с сервера (Сбор)
        List<String> logs = List.of(
            "10:01 FAILED user=admin ip=192.168.1.50",
            "10:02 FAILED user=admin ip=192.168.1.50",
            "10:03 FAILED user=admin ip=192.168.1.50",
            "10:04 SUCCESS user=admin ip=192.168.1.50"
        );

        int failedCount = 0;

        // Нормализация и Корреляция событий
        for (String log : logs) {
            if (log.contains("FAILED")) {
                failedCount++;
            } else if (log.contains("SUCCESS") && failedCount >= 3) {
                // Ескерту (Alert): 3+ ошибок входа, затем успешный взлом
                System.out.println("[SIEM ALERT] Возможный взлом (Brute-force + Успешный вход): " + log);
                failedCount = 0;
            }
        }
    }
}
