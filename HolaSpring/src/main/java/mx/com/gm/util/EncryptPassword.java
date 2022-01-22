package mx.com.gm.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EncryptPassword {
    public static void main(String[] args) {
        String pass = "123";
        System.out.println("Pass original: " + pass);
        System.out.println("Pass encriptado: " + encryptPass(pass));

    }

    // encriptar password con métodos de Spring
    public static String encryptPass(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }


}
