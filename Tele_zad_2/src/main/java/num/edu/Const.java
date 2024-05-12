import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.Scanner;

import com.sun.jna.platform.win32.*;
import static com.sun.jna.platform.win32.WinBase.*;
import static com.sun.jna.platform.win32.WinBase.COMMTIMEOUTS;

public class Const {
    // Control Characters
    public static final char SOH = 0x1;   // Start Of Heading
    public static final char EOT = 0x4;   // End Of Transmission
    public static final char ACK = 0x6;   // Acknowledge
    public static final char NAK = 0xF;   // Negative Acknowledge
    public static final char CAN = 0x12;  // Cancel

    // Port Information
    private static String portName;
    private static File uchwytPortu;
    private static WinNT.HANDLE handle;
    private static WinBase.DCB ustawieniaSterowania;
    private static WinBase.COMMTIMEOUTS ustawieniaCzasu;

    // Set Port Settings and Open
    public static boolean ustawieniaPortu(String numerPortu) {
        portName = numerPortu;
        uchwytPortu = new File(portName);

        if (!uchwytPortu.exists()) {
            System.out.println(Colors.YELLOW + "Port " + numerPortu + " nie istnieje." + Colors.RESET);
            return false;
        }

        handle = Kernel32.INSTANCE.CreateFile(
                portName,
                WinNT.GENERIC_READ | WinNT.GENERIC_WRITE,
                0,
                null,
                WinNT.OPEN_EXISTING,
                0,
                null
        );

        if (handle == WinBase.INVALID_HANDLE_VALUE) {
            System.out.println(Colors.YELLOW + "Nie można otworzyć portu " + numerPortu + "." + Colors.RESET);
            return false;
        }

        ustawieniaSterowania = new WinBase.DCB();
        if (!Kernel32.INSTANCE.GetCommState(handle, ustawieniaSterowania)) {
            System.out.println(Colors.YELLOW + "Błąd podczas pobierania ustawień portu." + Colors.RESET);
            Kernel32.INSTANCE.CloseHandle(handle);
            return false;
        }

        ustawieniaSterowania.BaudRate = 9600;
        ustawieniaSterowania.ByteSize = 8;
        ustawieniaSterowania.StopBits = 1;
        ustawieniaSterowania.Parity = 0;
        ustawieniaSterowania.fBinary = true;
        ustawieniaSterowania.fParity = false;

        ustawieniaCzasu = new WinBase.COMMTIMEOUTS();
        ustawieniaCzasu.ReadIntervalTimeout = 10;
        ustawieniaCzasu.ReadTotalTimeoutConstant = 10;
        ustawieniaCzasu.ReadTotalTimeoutMultiplier = 10;
        ustawieniaCzasu.WriteTotalTimeoutConstant = 10;
        ustawieniaCzasu.WriteTotalTimeoutMultiplier = 10;

        if (!Kernel32.INSTANCE.SetCommState(handle, ustawieniaSterowania)) {
            System.out.println(Colors.YELLOW + "Nie można ustawić parametrów portu." + Colors.RESET);
            Kernel32.INSTANCE.CloseHandle(handle);
            return false;
        }

        if (!Kernel32.INSTANCE.SetCommTimeouts(handle, ustawieniaCzasu)) {
            System.out.println(Colors.YELLOW + "Nie można ustawić czasów portu." + Colors.RESET);
            Kernel32.INSTANCE.CloseHandle(handle);
            return false;
        }

        System.out.println(Colors.GREEN + "Otwarto port " + numerPortu + Colors.RESET);
        return true;
    }

    // Close Port
    public static void zamknijPort() {
        if (handle != null && handle != WinBase.INVALID_HANDLE_VALUE) {
            Kernel32.INSTANCE.CloseHandle(handle);
            System.out.println(Colors.RED + "Zamknięto port " + portName + Colors.RESET);
        }
    }

    // Write Data to Port
    public static boolean zapiszDoPortu(String dane) {
        if (handle == null || handle == WinBase.INVALID_HANDLE_VALUE) {
            System.out.println(Colors.YELLOW + "Port nie jest otwarty." + Colors.RESET);
            return false;
        }

        byte[] buffer = dane.getBytes();
        IntByReference bytesWritten = new IntByReference();
        boolean success = Kernel32.INSTANCE.WriteFile(
                handle,
                buffer,
                buffer.length,
                bytesWritten,
                null
        );

        if (!success) {
            System.out.println(Colors.YELLOW + "Nie udało się zapisać do portu." + Colors.RESET);
            return false;
        }

        System.out.println(Colors.GREEN + "Wysłano dane: " + dane + Colors.RESET);
        return true;
    }

    // Read Data from Port
    public static String czytajZPortu() {
        if (handle == null || handle == WinBase.INVALID_HANDLE_VALUE) {
            System.out.println(Colors.YELLOW + "Port nie jest otwarty." + Colors.RESET);
            return "";
        }

        byte[] buffer = new byte[1024];
        IntByReference bytesRead = new IntByReference();
        boolean success = Kernel32.INSTANCE.ReadFile(
                handle,
                buffer,
                buffer.length,
                bytesRead,
                null
        );

        if (!success) {
            System.out.println(Colors.YELLOW + "Nie udało się odczytać z portu." + Colors.RESET);
            return "";
        }

        String dataReceived = new String(buffer, 0, bytesRead.getValue());
        System.out.println(Colors.GREEN + "Odczytano dane: " + dataReceived + Colors.RESET);
        return dataReceived;
    }
}
