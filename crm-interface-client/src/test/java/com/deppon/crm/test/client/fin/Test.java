package com.deppon.crm.test.client.fin;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) throws IOException {
//		FileWriter fileWriter=new FileWriter("c:\\Resultwu.txt");
//		SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
//		String a1=format.format(new Date());
//		fileWriter.write("\r\n"+a1);
//		fileWriter.flush();
//		 a1=format.format(new Date());
//		fileWriter.write("\r\n"+a1);
//		fileWriter.flush();
//		 a1=format.format(new Date());
//		fileWriter.write("\n\r"+a1);
//		fileWriter.flush();
//		Date dd=new Date(1386079201007l);
//		System.out.println(dd);
//		Date ddd=new Date(1386079561007l);
//		System.out.println(ddd);
		System.out.println(test());
	}
	public static boolean test(){
		try{
			System.out.println("try");
		}catch(Exception e){
			System.out.println("catch");
		}
		finally{
			System.out.println("finally");
			return true;
		}
	}
}
