package be.kdg.eirene.util;

import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

@Service
public class AEService {


	private final Random random = new Random();
	private final int MIN_LENGTH = 20;
	private final int MAX_LENGTH = 50;
	private final int KEY_LENGTH = random.nextInt(MAX_LENGTH - MIN_LENGTH) + MIN_LENGTH;
	@Getter
	private String secret;
	private SecretKeySpec secretKey;
	private byte[] key;

	@PostConstruct
	public void init() {
		secret = setSuperSecretKey();
	}

	private String setSuperSecretKey() {
		StringBuilder key = new StringBuilder();
		for (int i = 0; i < KEY_LENGTH; i++) {
			key.append((char) (random.nextInt(127 - 32) + 32));
		}
		return key.toString();
	}


	public void setKey(final String myKey) {
		MessageDigest sha;
		try {
			key = myKey.getBytes(StandardCharsets.UTF_8);
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String encrypt(final String strToEncrypt) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder()
			             .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e);
		}
		return null;
	}

	public String decrypt(final String strToDecrypt) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder()
			                                       .decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e);
		}
		return null;
	}
}
