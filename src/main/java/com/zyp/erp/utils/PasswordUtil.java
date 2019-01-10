package com.zyp.erp.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordUtil {

	/**
	 * 通过shiro加密密码.
	 * 
	 * @param source
	 *            未加密的密码
	 * @param salt
	 *            盐
	 * @return 加密之后的密码
	 */
	public static String encryptPassword(String source, String salt) {
		String result = new Md5Hash(source, salt).toString();
		return result;
	}

	/**
	 * 校验密码.
	 * 
	 * @param source
	 *            未加密的密码
	 * @param salt
	 *            盐
	 * @param encryptedPassword
	 *            加密之后的密码
	 * @return true or false
	 */
	public static Boolean verifyPassword(String source, String salt,
			String encryptedPassword) {
		String result = encryptPassword(source, salt);
		if (encryptedPassword.equals(result))
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		String encode = encryptPassword("1qaz2wsx", "admin");
		System.out.println(encode);
		System.out.println(verifyPassword("1qaz2wsx", "admin", encode));
	}

}
