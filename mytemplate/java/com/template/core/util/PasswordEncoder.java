package com.template.core.util;

public class PasswordEncoder {

	public static String encrypt(String src, Object salt) {
		return encrypt(src, String.valueOf(salt));
	}

	public static String encrypt(String src, String salt) {
		return EncryptUtil.MD5_HEX(EncryptUtil.MD5_HEX(src) + salt);
	}

	public static void main(String[] args) {
		System.out.println(encrypt("123456", "admin"));
	}
}
