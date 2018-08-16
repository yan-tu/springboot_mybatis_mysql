package com.yantu.sbmm.common.util;
/**
 * 字符串工具类
 * @author YanTu
 * 2018年6月19日
 */
public class StringOperateUtils {
	
	public static boolean isExist(String str) {
		if(str != null && !str.equals("")){
			return true;
		}
		return false;
	}
	
	public static boolean isNotExist(String str){
		if(str == null || "".equals(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * 纯数字验证
	 * @param inputStr
	 * @return
	 */
	public static boolean isPureNumber(String inputStr) {
		if(isNotExist(inputStr)) return false;
		String regStr = "^[+-]?[1-9][0-9]*$|^0$";
		return inputStr.matches(regStr);
	}
	
	/**
	 * 数字验证，包括小数、负数
	 * @param inputStr
	 * @return
	 */
	public static boolean isNumber(String inputStr){
		if(isNotExist(inputStr)) return false;
		String regStr = "^[+-]?(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
		return inputStr.matches(regStr);
	}
	
	public static void main(String[] args) {
		System.out.println(isNumber("-0.23"));
		System.out.println(isNumber("-23"));
		System.out.println(isNumber("23"));
		System.out.println(isNumber("23.5343"));
	}
}
