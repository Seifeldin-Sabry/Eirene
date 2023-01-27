package be.kdg.eirene.eirenespring;

import be.kdg.eirene.util.AEService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EncryptionDecryptionTests {

	private final AEService aes;

	@Autowired
	public EncryptionDecryptionTests(AEService aes) {
		this.aes = aes;
	}

	@Test
	void encryptionDecryptionTest() {
		String text = "This is a test";
		String encrypted = aes.encrypt(text);
		String decrypted = aes.decrypt(encrypted);
		Assertions.assertEquals(text, decrypted);
	}
}
