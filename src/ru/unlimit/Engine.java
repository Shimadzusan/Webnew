package ru.unlimit;

public class Engine {
	
	
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

}
