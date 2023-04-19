import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class RSA {
    // Función para calcular el máximo común divisor
public static int gcd(int a, int b) {
    if (b == 0) {
        return a;
    } else {
        return gcd(b, a % b);
    }
}

// Función para calcular el inverso multiplicativo
public static Integer modInv(int a, int m) {
    for (int x = 1; x < m; x++) {
        if ((a * x) % m == 1) {
            return x;
        }
    }
    return null;
}

// Función para cifrar el mensaje
public static int[] encrypt(String msg, int[] publicKey) {
    int e = publicKey[0];
    int n = publicKey[1];
    int[] encryptedMsg = new int[msg.length()];
    for (int i = 0; i < msg.length(); i++) {
        encryptedMsg[i] = (int) Math.pow((int) msg.charAt(i), e) % n;
    }
    return encryptedMsg;
}

// Función para descifrar el mensaje
public static String decrypt(int[] encryptedMsg, int[] privateKey) {
    int d = privateKey[0];
    int n = privateKey[1];
    char[] decryptedMsg = new char[encryptedMsg.length];
    int[] asciiValues = new int[encryptedMsg.length];
    for (int i = 0; i < encryptedMsg.length; i++) {
        decryptedMsg[i] = (char) ((char) Math.pow(encryptedMsg[i], d) % n);
        asciiValues[i] = (int) decryptedMsg[i];
    }
    return new String(decryptedMsg) + "\nValores ASCII: " + Arrays.toString(asciiValues);
}

// Función principal del programa
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // Pedimos al usuario que ingrese los números primos p y q
    System.out.print("Ingrese el número primo p: ");
    int p = scanner.nextInt();
    System.out.print("Ingrese el número primo q: ");
    int q = scanner.nextInt();

    // Calculamos n y phi(n)
    int n = p * q;
    int phiN = (p - 1) * (q - 1);

    // Pedimos al usuario que ingrese el número e que sea coprimo con phi(n)
    System.out.print("Ingrese el número e: ");
    int e = scanner.nextInt();
    while (gcd(e, phiN) != 1) {
        System.out.print("El número e debe ser coprimo con phi(n). Ingrese otro número e: ");
        e = scanner.nextInt();
    }

    // Calculamos d, el inverso multiplicativo de e módulo phi(n)
    Integer d = modInv(e, phiN);

    // Mostramos las claves pública y privada
    int[] publicKey = {e, n};
    int[] privateKey = {d, n};
    System.out.println("Clave pública: " + Arrays.toString(publicKey));
    System.out.println("Clave privada: " + Arrays.toString(privateKey));

    // Pedimos al usuario que ingrese el mensaje a cifrar
    System.out.print("Ingrese el mensaje a cifrar: ");
    String message = scanner.next();

    // Ciframos el mensaje
    int[] encryptedMessage = encrypt(message, publicKey);
    System.out.println("Mensaje cifrado: " + Arrays.toString(encryptedMessage));    
    }
}