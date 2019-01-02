package com.jeromet.multiplework;

import java.util.InputMismatchException;
import java.util.Random;

/** 
 * @author  作者: Jerome
 * @date 创建时间：2018年12月8日 下午7:10:13 
 * @version 5.0 
 * @description:  
 */

public class VerificationCode {	
	private int firstNum;
	private int secondNum;
	private int thirdNum;
	private String firstSymbol;
	private String secondSymbol;
	
	
	//生成算术表达式
	public String buildArichmetic() {
		Random random = new Random();
		firstNum = random.nextInt(9) + 1;
		secondNum = random.nextInt(9) + 1;
		thirdNum = random.nextInt(9) + 1;
		firstSymbol = setSymbol(random.nextInt(3));
		secondSymbol = setSymbol(random.nextInt(3));
		return firstNum + firstSymbol + secondNum + secondSymbol + thirdNum + "= ?";
	}
	
	//判断是否正确
	public boolean judgeArichmeticCorrectness(int result) {
		int sum = 0;
		if(getPriority(firstSymbol)>=getPriority(secondSymbol)) {
			sum = calculate(firstNum, secondNum, firstSymbol);
			sum = calculate(sum, thirdNum, secondSymbol);
		}else {
			sum = calculate(secondNum, thirdNum, secondSymbol);
			sum = calculate(firstNum, sum, firstSymbol);
		}
		System.out.println(sum);
		try {
			if(result == sum) {
				return true;
			}else {
				return false;
			}
		} catch (InputMismatchException e) {
			return false;
		}		

	}
	
	//生成随机符号
	private String setSymbol(int choice) {
		String symbol = null;
		switch (choice) {
		case 0:
			symbol = "+";
			break;
		case 1:
			symbol = "-";
			break;
		case 2:
			symbol = "*";
			break;
		}
		return symbol;
	}
	
	//获取算术符号的优先级
	private int getPriority(String symbol) {
		int priority = 0;
		switch(symbol) {
		case "+":
		case "-":
			priority = 0;
			break;
		case "*":
			priority = 1;
			break;
		}
		return priority;
	}
	
	//计算算术表达式
    private int calculate(int firstNum, int secondNum, String symbol) {
        int result = 0;
        switch (symbol) {
        case "+":
            result = firstNum + secondNum;
            break;
        case "-":
            result = firstNum - secondNum;
            break;
        case "*":
            result = firstNum * secondNum;
            break;
        }
        return result;
    }
	
}



