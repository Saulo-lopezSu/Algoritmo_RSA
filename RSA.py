import random

# Función para calcular el máximo común divisor
def gcd(a, b):
    if b == 0:
        return a
    else:
        return gcd(b, a % b)

# Función para calcular el inverso multiplicativo
def mod_inv(a, m):
    for x in range(1, m):
        if (a * x) % m == 1:
            return x
    return None

# Función para cifrar el mensaje
def encrypt(msg, public_key):
    e, n = public_key
    encrypted_msg = [pow(ord(char), e, n) for char in msg]
    return encrypted_msg

# Función para descifrar el mensaje
def decrypt(encrypted_msg, private_key):
    d, n = private_key
    decrypted_msg = [chr(pow(char, d, n)) for char in encrypted_msg]
    ascii_values = [ord(char) for char in decrypted_msg]
    return ''.join(decrypted_msg), ascii_values

# Función principal del programa
def main():
    # Pedimos al usuario que ingrese los números primos p y q
    p = int(input('Ingrese el número primo p: '))
    q = int(input('Ingrese el número primo q: '))

    # Calculamos n y phi(n)
    n = p * q
    phi_n = (p - 1) * (q - 1)

    # Pedimos al usuario que ingrese el número e que sea coprimo con phi(n)
    e = int(input('Ingrese el número e: '))
    while gcd(e, phi_n) != 1:
        e = int(input('El número e debe ser coprimo con phi(n). Ingrese otro número e: '))

    # Calculamos d, el inverso multiplicativo de e módulo phi(n)
    d = mod_inv(e, phi_n)

    # Mostramos las claves pública y privada
    public_key = (e, n)
    private_key = (d, n)
    print('Clave pública:', public_key)
    print('Clave privada:', private_key)

    # Pedimos al usuario que ingrese el mensaje a cifrar
    message = input('Ingrese el mensaje a cifrar: ')

    # Ciframos el mensaje
    encrypted_message = encrypt(message, public_key)
    print('Mensaje cifrado:', encrypted_message)

    # Desciframos el mensaje
    decrypted_message, ascii_values = decrypt(encrypted_message, private_key)
    print('Mensaje descifrado:', decrypted_message)
    print('Valores ASCII:', ascii_values)

if __name__ == '__main__':
    main()