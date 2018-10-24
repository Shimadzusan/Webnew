package ru.unlimit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
	static String main_text = "";
	public static void main(String[] args) {
		File text_from_file = new File("C:\\Users\\Stalin\\workspace\\Webnew\\WebContent\\NewFile.html");//*input
		Scanner scan_main_text = null;
			// C:\Users\Stalin\workspace\Webnew\WebContent
			try {
			scan_main_text = new Scanner(text_from_file);
			} 
			catch (FileNotFoundException e) {
			e.printStackTrace();
			}
					  
				while(scan_main_text.hasNextLine()){		
					main_text += scan_main_text.nextLine() + " ";
				
				}
				scan_main_text.close();
				System.out.println(main_text);

	}

}
