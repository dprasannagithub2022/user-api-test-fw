package com.apex.samples.test.Padma;

public class StringReverseProgram {

	public static void main(String[] args) {
			
			String str = "welcome";
			String revStr = "";
			int len = str.length();
			for(int i=len-1;i>=0;i--) {
				char c = str.charAt(i);
				revStr+= c;
			}
	        System.out.println("Reverse String is:"+ revStr);
		}

	}


