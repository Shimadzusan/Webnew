package ru.unlimit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int count = 0;
	//public String s = "Когнитивно-логический почерк автора";

    public MyServlet() {
        
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		request.setCharacterEncoding("UTF-8");
	
		
//		String name = request.getParameter("FirstName");
//		String family = request.getParameter("SecondName");
//		System.out.println(name);
//		System.out.println(family);
		
		String comment = request.getParameter("comment");
		count++;
		System.out.println("I am MyServlet");
		PrintWriter out = response.getWriter();
		
//response.setAttribute("username", name);
		
//======================Получаем параметры от RadioButton!
		
		String sub_name = request.getParameter("dzen1");
		String sub_number = request.getParameter("dzen2");
		String sub_structure = request.getParameter("dzen3");
		
		System.out.println("1: " + sub_name + " 2: " + sub_number + " 3: " + sub_structure);
		
		boolean names = false;
		boolean number = false;
		boolean structure = false;
		
		if(sub_name != null)if(sub_name.equals("имя")) names = true;
		if(sub_number != null)if(sub_number.equals("число")) number = true;
		if(sub_structure != null)if(sub_structure.equals("структура")) structure = true;	
		
//========================================================
		
//////////////////////////////////MAIN_MODUL/////////////////////////////////////////////////////////////
		/*
		 * Main_modul функционирует следующим образом:
		 * Читаем html-код копии главной страницы ! без завершающих тегов, и преобразуем ее в текст
		 * Производим рассчеты над текстом поступившим от клиента, все расчеты преобразуем в текст,
		 * и добавляем завершающие теги
		 * html-страница таким образом сгенерирована
		 * Объединяем оба текста и отправляем клиенту
		 * */
	String main_text = "";
	String second_text = "";
//Читаем первую часть
	File text_from_file = new File("C:\\Users\\Stalin\\workspace\\Webnew\\WebContent\\Part1.html");
		Scanner scan_main_text = null;
			
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
//Читаем завершающую часть
					
//					File text_from_file_part_two = new File("C:\\Users\\Stalin\\workspace\\Webnew\\WebContent\\Part2.html");
//					Scanner scan_main_text_part_two = null;
//						
//						try {
//						scan_main_text_part_two = new Scanner(text_from_file_part_two);
//						} 
//						catch (FileNotFoundException e) {
//						e.printStackTrace();
//						}		  
//							while(scan_main_text_part_two.hasNextLine()){		
//								second_text += scan_main_text_part_two.nextLine() + " ";	
//							}
//								scan_main_text_part_two.close();
					
//Генерируем отчет					
	Engine engine = new Engine();
	
	
	Clever clever = new Clever();
		//clever.getReport(comment, names,  number, structure);
	
		//String part_two = "<! Начало генерируемой html-части> <a href='http://localhost:8080/Webnew/NewFile.html'>Home</a> <br>" + count + "<br>Результат анализа. <br>количество слов: " + engine.words_volume(comment) + "<! Конец генерируемой html-части><p>Copyright icon</p></body></html>";			
		
		String part_three = clever.getReport(comment, names,  number, structure) + "<! Конец генерируемой html-части><p>Copyright icon</p></body></html>";
		String omega = main_text + part_three;
		out.println(omega);
//		out.print("<a href='http://localhost:8080/Webnew/NewFile.html'>Home</a>");
//		out.print("Hello guys");
		out.close();
//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
