package be.kdg.eirene.util;


import static org.springframework.security.crypto.bcrypt.BCrypt.*;
import static org.springframework.security.crypto.bcrypt.BCrypt.gensalt;

public class BcryptPasswordUtil {
    public static String hashPassword(String password) {
        return hashpw(password, gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return checkpw(password, hashedPassword);
    }
}
