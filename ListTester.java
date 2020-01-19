//Name: Leo Tran Student ID: 215701360

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListTester {
	private static String temp;//Temp strings to help add the inputs into the file.
	private static String temp2;
	private static String temp3;
	public static void main(String[] args) {
		ArrayList<Number> fileA = new ArrayList<>();
        FileList<Number> fileB = new FileList<>("Existing.txt");
        
        ArrayList<Number> fileC = new ArrayList<>();
        FileList<Number> fileD = new FileList<>("Existing.txt");
        
        ArrayList<Number> fileE = new ArrayList<>();
        FileList<Number> fileF = new FileList<>("Existing.txt");
        
        
        
        
        System.out.println("\nPart A:\n");
        putStart(10, fileA);
        putStart(10, fileB);
		
        putEnd(10, fileA);
        putEnd(10, fileB);
        
        putAny(10, fileA);
        putAny(10, fileB);
        System.out.println("\nPart B:\n");
        fillFile(10, fileC);
        fillFile(10, fileD);
        
        removeStart(10, fileC);
        removeStart(10, fileD);
        fillFile(10, fileC);
        fillFile(10, fileD);
        
        removeEnd(10, fileC);
        removeEnd(10, fileD);
        fillFile(10, fileC);
        fillFile(10, fileD);
        
        removeAny(10, fileC);
        removeAny(10, fileD);
        System.out.println("\nPart C:\n");
        
        fillFile(10, fileE);
        fillFile(10, fileF);
        removeValue(10, fileE);
        removeValue(10, fileF);
        //N = 100
        System.out.println("\nPart A:\n");
        putStart(100, fileA);
        putStart(100, fileB);
		
        putEnd(100, fileA);
        putEnd(100, fileB);
        
        putAny(100, fileA);
        putAny(100, fileB);
        System.out.println("\nPart B:\n");
        
        fillFile(100, fileC);
        fillFile(100, fileD);
        removeStart(100, fileC);
        removeStart(100, fileD);
        
        fillFile(100, fileC);
        fillFile(100, fileD);       
        removeEnd(100, fileC);
        removeEnd(100, fileD);
        
        fillFile(100, fileC);
        fillFile(100, fileD);
        removeAny(100, fileC);
        removeAny(100, fileD);
        System.out.println("\nPart C:\n");
        
        fillFile(100, fileE);
        fillFile(100, fileF);
        removeValue(100, fileE);
        removeValue(100, fileF);
        System.out.println("\n");
        File file0 = new File("ListTester.txt");
        try {
			BufferedWriter f1 = new BufferedWriter(new FileWriter(file0));
			f1.write("Part A: \n" +  temp.substring(4) + "\nPart B: \n" + temp2.substring(4) + "\nPart C: \n" + temp3.substring(4));
			f1.flush();
			f1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	/**
	 * 
	 * @param number
	 * @param list
	 */
	public static void fillFile(int number, List list) {
		list.clear();
		int random;
		for (int i = 0; i < number; i++) {
			random = (int)(Math.random()*(10*number));
			list.add(random);
		}
	}
	
	//PART A---------------------------------------------------
	/**
	 * Function adds to the start of the list.
	 * @param number
	 * @param list
	 */
	public static void putStart(int number, List list) {
		int random;
		Long startTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			random = (int)(Math.random()*(10*number));
			list.add(0, random);
		}
		Long endTime = System.currentTimeMillis() - startTime; 
		temp += list.getClass().getSimpleName() + ": For N = " + number + ", Add To Start, end time = "+ endTime + "\n"; 
		System.out.println(list.getClass().getSimpleName() + ": For N = " + number + ", Add To Start, end time = "+ endTime);
        list.clear();
	}
	/**
	 * Function adds to the end of the list.
	 * @param number
	 * @param list
	 */
	public static void putEnd(int number, List list) {
		int random;
		Long startTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			random = (int)(Math.random()*(10*number));
			list.add(random);
		}
		Long endTime = System.currentTimeMillis() - startTime; 
		temp += list.getClass().getSimpleName() + ": For N = " + number + ", Add To End, " + "end time = " + endTime + "\n";
		System.out.println(list.getClass().getSimpleName() + ": For N = " + number + ", Add To End, " + "end time = " + endTime);
        list.clear();
	}
	/**
	 * Function adds to a random index of the list.
	 * @param number
	 * @param list
	 */
	public static void putAny(int number, List list) {
		int random;
		int randomI;
		Long startTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			random = (int)(Math.random()*(10*number));
			randomI = (int)(Math.random()*i);
			list.add(list.size(), random);
		}
		Long endTime = System.currentTimeMillis() - startTime; 
		temp += list.getClass().getSimpleName() + ": For N = " + number + ", Add To Random Location, " + "end time = " + endTime + "\n";
		System.out.println(list.getClass().getSimpleName() + ": For N = " + number + ", Add To Random Location, " + "end time = " + endTime);
        list.clear();
	}
	//Part B:
	/**
	 * Function removes at the start of the list.
	 * @param number
	 * @param list
	 */
	public static void removeStart(int number, List list) {
		int random;
		Long startTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			random = (int)(Math.random()*(10*number));
			list.remove(0);
		}
		Long endTime = System.currentTimeMillis() - startTime; 
		temp2 += list.getClass().getSimpleName() + ": For N = " + number + ", Delete To Start, end time = "+ endTime + "\n";
		System.out.println(list.getClass().getSimpleName() + ": For N = " + number + ", Delete To Start, end time = "+ endTime);
        list.clear();
	}
	/**
	 * Function removes at the end of the list.
	 * @param number
	 * @param list
	 */
	public static void removeEnd(int number, List list) {
		int random;
		Long startTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			random = (int)(Math.random()*(10*number));
			list.remove(number-i-1);
		}
		Long endTime = System.currentTimeMillis() - startTime; 
		temp2 += list.getClass().getSimpleName() + ": For N = " + number + ", Delete To End, end time = "+ endTime + "\n";
		System.out.println(list.getClass().getSimpleName() + ": For N = " + number + ", Delete To End, end time = "+ endTime);
        list.clear();
	}
	/**
	 * Function removes at a random index of the list.
	 * @param number
	 * @param list
	 */
	public static void removeAny(int number, List list) {
		int random;
		int randomI;
		Long startTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			random = (int)(Math.random()*(number-i));
			list.remove(random);
		}
		Long endTime = System.currentTimeMillis() - startTime; 
		temp2 +=list.getClass().getSimpleName() + ": For N = " + number + ", Delete To Random Location, " + "end time = " + endTime + "\n";
		System.out.println(list.getClass().getSimpleName() + ": For N = " + number + ", Delete To Random Location, " + "end time = " + endTime);
        list.clear();
	}
	//Part C:
	/**
	 * Function removes a random value from the list.
	 * @param number
	 * @param list
	 */
	public static void removeValue(int number, List list) {
		int temp4 = number*10;
		Long startTime = System.currentTimeMillis();
		while (list.size() > 0) {
			Integer random = (int)(Math.random()*(temp4));
			list.remove(random);
		}
		Long endTime = System.currentTimeMillis() - startTime;
		temp3 += list.getClass().getSimpleName() + ": For N = " + number + ", Removing By Value, " + "end time= " + endTime + "\n";
		System.out.println(list.getClass().getSimpleName() + ": For N = " + number + ", Removing By Value, " + "end time = " + endTime);
        list.clear();
	}
}
