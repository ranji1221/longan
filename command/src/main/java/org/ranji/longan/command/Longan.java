package org.ranji.longan.command;

import java.util.Scanner;

/**
 * Longan框架主命令引导类
 * 
 */
public class Longan {
    public static void main( String[] args ) {  	
    	printFrameInfo();
    	for(;;){
    		promptInfo();
    		//-- 1.  处理输入的命令字符串为标准的中间只空单空白符而且两边没有空白符的字符串
        	String result = handleString(readLine());
        	//-- 2. 处理命令字符串为标准的字符串数组，数组中的字符串确保两边没有空白符
        	String[] resultArr = splitString(result);
        	int length = resultArr.length;
        	//-- 3. 判断同时输入的是多少个命令
        	switch (length) {
			case 1:
				if("list".equalsIgnoreCase(result)){
	        		printCmdsInfo();
	        		System.out.println();
	        	}else if("quit".equalsIgnoreCase(result)){
	        		System.out.println("Good-Bye\r\n");
	        		System.exit(0);
	        	}else if("create".equalsIgnoreCase(result)){
	        		printCreateInfo();
	        	}
				continue;
			case 2:
				if("create".equalsIgnoreCase(resultArr[0])){
					printCreateInfo();
				}
				continue;
			case 3:
				if("create".equalsIgnoreCase(resultArr[0])){
					
				}
			default:
				continue;
        	}
    	}
    }
    
    public static void printCreateInfo(){
    	System.out.println("Usage: create  app|module|plugin  appName|moduleName|pluginName targetDir");
    	System.out.println("Example: create app com.company.projectname D:\\\r\n");
    }
    
   /**
    * 处理输入的命令字符串
    * 1. 若输入的仅是单命令，则处理为祛除两边的空白符
    * 2. 若输入的是多命令，则处理为每个命令仅间隔单空格符的字符串 
    * @param result
    * @return
    */
   public static String handleString(String result){ 
	   StringBuilder sb = new StringBuilder("");
	   String[] temp = result.trim().split("\\s+");
	   for(int i=0;i<temp.length;i++){
		   sb.append(temp[i].trim()+" ");
	   }
	   return sb.toString().trim();
   }
   /**
    * 处理输入的命令字符串，变为没有空白符字符串的数组
    * @param result
    * @return
    */
   public static String[] splitString(String result){
	   String[] oldArr = result.trim().split("\\s+");
	   String[] newArr =new String[oldArr.length];
	   for(int i=0;i<newArr.length;i++){
		   newArr[i] = oldArr[i].trim();
	   }
	   return newArr;
   }
    
    public static void printFrameInfo(){
    	System.out.println("Welcome to Longan Framework (version 1.0)");
    	System.out.println("Copyright (c) 2013   Ji Ran  All rights reserved.");
    	System.out.println("Contact: QQ(95724368),Email(jiran1221@163.com)\r\n");
    	
    	System.out.println("Run 'list' command to display All enable commands.");
    	System.out.println("Run 'help <command> ' to display help for specific command.\r\n");
    }
    
    public static void promptInfo(){
    	System.out.print("longan>");
    }
    
    public static void printCmdsInfo(){
    	System.out.println("list \t\t list all enable commands.");
    	System.out.println("create \t\t create project or module.");
    	System.out.println("quit \t\t quit the longan framework");
    }
   
    public static String readLine(){
    	Scanner s = new Scanner(System.in);
    	String str = "";
    	try {
			str = s.nextLine();
		} catch (Exception e) {
			System.out.println();
			str = "quit";
		}
    	return str;
    }
    
}
