package com.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TerminalUtils {
	public static Scanner sc = new Scanner(System.in);
	
	public static void out(String text) {
		System.out.println(text);
	}
	
	public static int getInt() {
	    while (true) {
	        try {
	            return Integer.parseInt(sc.nextLine().trim());
	        } catch (NumberFormatException e) {
	            out("Introduce una opción correcta:");
	        }
	    }
	}
	
	public static float getFloat() {
	    while (true) {
	        try {
	            String input = sc.nextLine();
	            return Float.parseFloat(input);
	        } catch (NumberFormatException e) {
	            System.out.println("Introduce un número decimal válido:");
	        }
	    }
	}


	public static String getString() {
		String text = sc.nextLine();
		return text;
	}
	
	public static boolean validarFormatoFecha(String fechaStr) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    try {
	        @SuppressWarnings("unused")
			LocalDate fecha = LocalDate.parse(fechaStr, formatter);
	        return true;
	    } catch (DateTimeParseException e) {
	        return false;
	    }
	}
}
