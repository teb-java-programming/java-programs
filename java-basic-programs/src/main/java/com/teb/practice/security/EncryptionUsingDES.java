package com.teb.practice.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncryptionUsingDES {

	private static void encryptData(String encryptionKey, InputStream inputStream, OutputStream outputStream) {

		dataEncryptDecrypt(encryptionKey, Cipher.ENCRYPT_MODE, inputStream, outputStream);
	}

	private static void decryptData(String encryptionKey, InputStream inputStream, OutputStream outputStream) {

		dataEncryptDecrypt(encryptionKey, Cipher.DECRYPT_MODE, inputStream, outputStream);
	}

	private static void dataEncryptDecrypt(String encryptionKey, int actionMode, InputStream inputStream,
			OutputStream outputStream) {

		try {
			DESKeySpec desKeySpec = new DESKeySpec(encryptionKey.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			Cipher cipher = Cipher.getInstance("DES");

			if (actionMode == Cipher.ENCRYPT_MODE) {
				cipher.init(actionMode, secretKey);
				CipherInputStream cipherStream = new CipherInputStream(inputStream, cipher);
				doCopy(cipherStream, outputStream);
			} else if (actionMode == Cipher.DECRYPT_MODE) {
				cipher.init(actionMode, secretKey);
				CipherOutputStream cipherStream = new CipherOutputStream(outputStream, cipher);
				doCopy(inputStream, cipherStream);
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| IOException e) {
			e.printStackTrace();
		}
	}

	private static void doCopy(InputStream inputStream, OutputStream outputStream) throws IOException {

		byte[] dataInBytes = new byte[64];
		int numberBytes;

		while ((numberBytes = inputStream.read(dataInBytes)) != -1) {
			outputStream.write(dataInBytes, 0, numberBytes);
		}

		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}

	public static void main(String[] args) {

		// TheEncodedBongByKuntal
		String encryptionKey = System.getenv("encryptionKey");

		try {
			// Call to encrypt
			FileInputStream inputEncryptionStream = new FileInputStream("src/main/resources/des-plain-text.json");
			FileOutputStream outputEncryptionStream = new FileOutputStream("src/main/resources/des-cipher-text.enc");
			encryptData(encryptionKey, inputEncryptionStream, outputEncryptionStream);

			// Call to decrypt
			FileInputStream inputDecryptionStream = new FileInputStream("src/main/resources/des-cipher-text.enc");
			FileOutputStream outputDecryptionStream = new FileOutputStream("src/main/resources/des-plain-text.json");
			decryptData(encryptionKey, inputDecryptionStream, outputDecryptionStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
