package com.qiyue.bluecareer.utils;

import com.qiyue.bluecareer.Exception.EncryptException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Qiyue on 2017/10/13
 */

public class EncryptUtil {

    private static final String S_KEY = "abcdef0123456789";
    private static final String IV_PARAMETER = "0123456789abcdef";

    private EncryptUtil() {
        /**/
    }

    public static String encryptSHA(String password) throws EncryptException {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA");
            byte[] srcBytes = password.getBytes();
            md.update(srcBytes);
            byte[] result = md.digest();
            BigInteger sha = new BigInteger(result);
            return sha.toString(64);
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException(e);
        }
    }

    // AES加密
    public static String encryptAES(String sSrc) throws EncryptException {
        String result;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = S_KEY.getBytes();
            SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            result = Base64.encodeBase64String(encrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                InvalidKeyException | BadPaddingException | IllegalBlockSizeException |
                UnsupportedEncodingException e) {
            throw new EncryptException(e);
        }
        // 此处使用BASE64做转码。
        return result;

    }

    //AES解密
    public static String decryptAES(String sSrc) throws EncryptException {
        byte[] raw;
        String originalString;
        try {
            raw = S_KEY.getBytes("ASCII");
            SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
            byte[] encrypted1 = Base64.decodeBase64(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            originalString = new String(original, "utf-8");
        } catch (NoSuchAlgorithmException | IOException | InvalidKeyException | InvalidAlgorithmParameterException
                | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            throw new EncryptException(e);
        }
        return originalString;
    }
}

