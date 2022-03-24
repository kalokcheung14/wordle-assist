package com.wordle_assist;

import java.util.HashSet;

public class Utilities {
	// Singleton pattern
	protected static Utilities instance = new Utilities();

	  public Utilities() {
	
	  }
  
	  public Utilities getInstance() {
		  return instance;
	  }
	
	  protected HashSet<Character> stringToSet(String str) {
	      str = str.toLowerCase();
	      HashSet<Character> charList = new HashSet<Character>();
	
	      for (int i = 0; i < str.length(); i++) {
	          charList.add(str.charAt(i));
	      }
	
	      return charList;
	  }
}
