import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz numer portu (1-4):");
        String numerPortu = scanner.nextLine().trim();

        switch (numerPortu) {
            case "1":
                numerPortu = "COM1";
                break;
            case "2":
                numerPortu = "COM2";
                break;
            case "3":
                numerPortu = "COM3";
                break;
            case "4":
                numerPortu = "COM4";
                break;
            default:
                numerPortu = "COM2"; // Default to COM2
                break;
        }

        boolean portOpened = Const.ustawieniaPortu(numerPortu);

        if (!portOpened) {
            System.out.println("Nie można otworzyć portu.");
            return;
        }

        // Interfejs do wysyłania danych
        System.out.println("Wpisz dane do wysłania:");
        String dane = scanner.nextLine();

        boolean dataSent = Const.zapiszDoPortu(dane);
        if (!dataSent) {
            System.out.println("Błąd podczas wysyłania danych.");
        }

        // Interfejs do odczytu danych
        System.out.println("Naciśnij Enter, aby odczytać dane z portu...");
        scanner.nextLine();

        String receivedData = Const.czytajZPortu();
        if (receivedData.isEmpty()) {
            System.out.println("Brak danych do odczytu.");
        }

        Const.zamknijPort();
    }
}
