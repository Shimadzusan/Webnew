package ru.unlimit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Clever {
	String main_text = "";
	boolean name = false;
	boolean number = false;
	boolean structure = false;
	
	public String getReport(String text, boolean names, boolean numbers, boolean structure){
		/*
		 * Получаем текст и логические параметры: Имя, Чило, Структура текста
		 * В зависимости от состояния логического параметра(истина/ложь) происходит вызов
		 * одноименног метода
		 */
		
		this.name = names;
		this.number = numbers;
		this.structure = structure;
		
		setText(text);
		String html_code = build_html();
		
		return html_code;
	}
	
	public String build_html(){
		
		String html = "<br>количество символов: с пробелами - " + getSymbol() + ", без пробелов - " + (main_text.length() - words_volume(main_text)) + 
				"<br>количество слов: " + words_volume(main_text) + 
				"<br>словарный запас: " + getVocabulary() + 
				"<br>количество предложений: " + getSatz() + 
				"<br>Имена: " + getNames(name) +  
				"<br>Числа: " + getNumbers(number) + 
				"<br>Структура текста: " + getStructure(structure);
		
		return html;
	}
	
	public int words_volume(String s_file) {
		//..COUNT ALL WORDS FROM DATATEXT
					String[] text = s_file.split(" ");			//подсчет слов по количеству пробелов
					int a = text.length;
					
					//сборка слов вновь в текст с пробелами
					String result = "";
					for(int i = 0; i < text.length; i++ ){
					result = result + " " + text[i];
					}
			return a;
		}
	
	public int getSymbol(){
		int lot_all_symbol = main_text.length();
		return lot_all_symbol;
	}
	
	public void setText(String text){
		this.main_text = text;
	}
	
	public int getVocabulary(){//..COUNT VOLUME DIFFRENT WORDS FROM DATATEXT
		//..словарный запас
		int difference = 0;
		String text = getClear_text(main_text);			
		String[] array_all_words = text.split(" ");
		HashSet<String> set = new HashSet<String>(); //вычисление различных слов через SET
					
			for(int i = 0; i < array_all_words.length; i++) {
						set.add(array_all_words[i]);
			}
					difference = set.size();
		return difference;
	}
	public int getSatz(){
		//..COUNT ALL SATZ FROM DATATEXT
		int point_count = 0;
		char[] array_of_symbol = main_text.toCharArray();			//подсчет предложений по количеству точек
				
			for(int i = 0; i < array_of_symbol.length; i++) {
					if(array_of_symbol[i] == '.') point_count++;
			}
		return point_count;
	}
	
	public String getNames(boolean flag){
		
		/*
		 * Метод names() извлекает из текста Имена следующим образом:
		 * Исходный текст преобразуется в массив символов
		 * В массиве символов осуществляется поиск последовательности символов,
		 * в данном случае это - символ верхнего регистра + пробел слева + точка после пробела,
		 * либо другие варианты
		 * Обнаружив искомую последовательность символов, формируется строка, которая и есть Имя
		 * Имена добавляются в список
		 * 
		 * В силу специфики алгоритма в начало и конец текста намерено добавлене "абракадабра",
		 * пока это решение под вопросом
		 */
		
		if(flag != true) return "name is false";
		
		String kostil = "dfjhdjfhjd. " + main_text + " dfhskdfhksdfkjsdfj";
		char[] array_all_symbol = kostil.toCharArray();
		char symbol;
		String name = "";
		ArrayList<String> list_of_name = new ArrayList<String>();
		
		String result = "";
		
			for(int i = 0; i < array_all_symbol.length; i++){
				symbol = array_all_symbol[i];
	//Логика: ...
					if(Character.isUpperCase(symbol) && array_all_symbol[i-1] == ' ' && array_all_symbol[i-2] != '.' && array_all_symbol[i-3] != '.' || Character.isUpperCase(symbol) && array_all_symbol[i-1] != ' '){
	//Формирование строки: символы преобразуются в строку до пробела
							while(array_all_symbol[i] != ' '){
								name +=  array_all_symbol[i];
								i++;
							}
									result += "<br>" + getClear_text2(name);
									name = "";
					}
			}
			
//			for(String element : list_of_name){
//				System.out.println(element);
//			}
			

		
		
		return result;
	}
	
public String getNumbers(boolean flag){
	
	/*
	 * Метод number() извлекает из текста числа следующим образом:
	 * Исходный текст преобразуется в массив слов
	 * Каждое слово массива преобразуется в массив символов
	 * В массиве символов осуществляется поиск чисел
	 * Если находим число, извлекаем все слово, и переходим к следующему слову
	 */
	
	if(flag != true) return "number is false";
	
	String[] list_all_words = main_text.split(" ");
	String word_of_list_all_words = "";
	char[] list_of_sybol;
	char symbol;
	
	ArrayList<String> list_all_numbers = new ArrayList<String>();
	
		for(int i = 0; i < list_all_words.length; i++){
			word_of_list_all_words = list_all_words[i];
			list_of_sybol = word_of_list_all_words.toCharArray();		
//Ищем в слове (которое представлено набором символов) числа, и если находим, то извлекаем все слово			
				for(int k = 0; k < list_of_sybol.length; k++){
					symbol = list_of_sybol[k];
					
						if(Character.isDigit(symbol)){
							//System.out.println(word_of_list_all_words);
							list_all_numbers.add(word_of_list_all_words);
								break;	
						}
				}
		}
		String result = "<br> NUMBERS:";
		for(int i = 0; i < list_all_numbers.size(); i++){
			result += "<br>" + list_all_numbers.get(i);
		}
		
		return result;
	}

public String getStructure(boolean flag){
	
	/*
	 * Метод text_structure() выявляет структуру текста следующим образом:
	 * Исходный текст очищается от лишних символов(запятые, точки и т.д.)
	 * Очищенный текст преобразуется в массив слов
	 * Из массива слов образуется два операционных массива: set and list
	 * Новый операционный массив map_of_heap содержит количество упоминаний элементов массива set
	 * в массиве list. Что и осталось упорядочить по возрастанию
	 * В массиве map_of_heap осуществляется поиск самого большого числа, затем поиск строки с этим самым
	 * большим числом, далее запись этой строки с самым большим числом в новый массив sorted_list,
	 * и удаление отсортированной записи из map_of_heap
	 * */
	
	if(flag != true) return "structure is false";
	
	
	String text = getClear_text(main_text);
	String[] list_all_words = text.split(" ");
	
	String result = "";
//**********************************************************************	
		HashSet<String> set = new HashSet<String>();
			for(int i = 0; i < list_all_words.length; i++) {
				set.add(list_all_words[i]);
			}
	
		ArrayList<String> list = new ArrayList<String>();
			for(String element : list_all_words){
				list.add(element);
			}
//**********************************************************************
			
	HashMap<String, Integer> map_of_heap = new HashMap<String, Integer>();
	int count = 0;
	
		for(String element_of_set : set){
			
				for(String element_of_list : list){
					if(element_of_set.equals(element_of_list))count++; 	
				}
					map_of_heap.put(element_of_set, count);
					count = 0;
		}
//**********************************************************************
	ArrayList<String> sorted_list = new ArrayList<String>();
	String element_of_sorted_list = "";
	int big = 0;
	
	while(map_of_heap.size() != 0){
		
		for(HashMap.Entry<String, Integer> super_number : map_of_heap.entrySet()){
//Осуществляем поиск самого большого числа из map_of_heap
			if(big < super_number.getValue())big = super_number.getValue();
		}
		
			for(HashMap.Entry<String, Integer> row : map_of_heap.entrySet()){
//Осуществляем поиск KEY с самым большим числом в map_of_heap	
				if(row.getValue() == big){
					element_of_sorted_list = row.getKey() + " | " + row.getValue();
						sorted_list.add(element_of_sorted_list);
//Удаление и обнуление
							map_of_heap.remove(row.getKey(), row.getValue());
								big = 0;
									break;
				}
			}
	}
	
//	System.out.println("++++++++++++++++++++++++");
//		for(String element : sorted_list){
//			System.out.println(element);
//		}
	
	for(int i = 0; i < sorted_list.size(); i++){
		result += "<br>" + sorted_list.get(i);
	}
	
	
	return result;
}
	
	public String getClear_text(String fragment){
		//..DELETE ALL TRASH_SYMBOL FROM DATATEXT
		//..удаление лишних символов(точки, запятые и др.) и установление единого регистра
					char[] array_of_symbol = fragment.toCharArray();
					ArrayList<Character> list = new ArrayList<Character>();
					String final_fragment = "";
					
						for(int i = 0; i < array_of_symbol.length; i++) {
							array_of_symbol[i] = Character.toLowerCase(array_of_symbol[i]);
							if(array_of_symbol[i] != '.' && array_of_symbol[i] != ',' && array_of_symbol[i] != '"' ) list.add(array_of_symbol[i]);
						}
							
							for(int i = 0; i < list.size(); i++){
								final_fragment += list.get(i);	
							}
			return final_fragment;
		}
	
	
	public String getClear_text2(String fragment){
		//..DELETE ALL TRASH_SYMBOL FROM DATATEXT
		//..удаление лишних символов(точки, запятые и др.) 
					char[] array_of_symbol = fragment.toCharArray();
					ArrayList<Character> list = new ArrayList<Character>();
					String final_fragment = "";
					
						for(int i = 0; i < array_of_symbol.length; i++) {
							if(array_of_symbol[i] != '.' && array_of_symbol[i] != ',' && array_of_symbol[i] != '"' && array_of_symbol[i] != ')' && array_of_symbol[i] != '»') list.add(array_of_symbol[i]);
						}
							
							for(int i = 0; i < list.size(); i++){
								final_fragment += list.get(i);	
							}
			return final_fragment;
		}

}
