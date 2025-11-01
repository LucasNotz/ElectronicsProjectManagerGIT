package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class test3 {
	  public static void main(String[] args) {
		    try {
		      File myObj = new File("filename.txt"); // Create File object
		      if (myObj.createNewFile()) {           // Try to create the file
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace(); // Print error details
		    }
		    try {
		        FileWriter myWriter = new FileWriter("filename2.txt");
		        myWriter.append("Files in Java might be tricky, but it is fun enough!");
		        myWriter.close();  // must close manually
		        System.out.println("Successfully wrote to the file.");
		      } catch (IOException e) {
		        System.out.println("An error occurred.");
		        e.printStackTrace();
		      }
		    File myObj = new File("filename.txt");

		    // try-with-resources: Scanner will be closed automatically
		    try (Scanner myReader = new Scanner(myObj)) {
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		    }
		  }
	  

