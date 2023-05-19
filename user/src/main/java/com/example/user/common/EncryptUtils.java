//package com.example.user.common;
//
//import cn.hutool.core.text.CharSequenceUtil;
//import cn.hutool.core.util.CharsetUtil;
//import cn.hutool.crypto.SecureUtil;
//import cn.hutool.crypto.symmetric.AES;
//import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
//import lombok.SneakyThrows;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.DESKeySpec;
//import java.security.SecureRandom;
//
///**
// * desc: 密码加密工具类
// *
// * @author: xwx
// * @mail: 12306@qq.com
// * @create 2018-08-03 09:13
// */
//public class EncryptUtils {
//
//
//    public static void main(String[] args) {
//        String content = "test中文";
//
//// 随机生成密钥
//        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
//
//// 构建
//        AES aes = SecureUtil.aes(key);
//
//// 加密
//        byte[] encrypt = aes.encrypt(content);
//// 解密
//        byte[] decrypt = aes.decrypt(encrypt);
//
//// 加密为16进制表示
//        String encryptHex = aes.encryptHex(content);
//        System.out.println("encryptHex="+ encryptHex);
//// 解密为字符串
//        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
//        System.out.println("decryptStr="+ decryptStr);
//    }
//
//    public static String doEncrypt(String password) {
//
//// 随机生成密钥
//        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
//
//// 构建
//        AES aes = SecureUtil.aes(key);
//
//// 加密
//        byte[] encrypt = aes.encrypt(password);
//// 解密
//        byte[] decrypt = aes.decrypt(encrypt);
//        String encryptHex = aes.encryptHex(password);
//        System.out.println("encryptHex="+ encryptHex);
//// 解密为字符串
//        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
//        System.out.println("decryptStr="+ decryptStr);
//        return encryptHex;
//    }
//}
