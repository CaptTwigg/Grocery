package Grocery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class GroceryApplication {


	public static void main(String[] args) throws FileNotFoundException {

		SpringApplication.run(GroceryApplication.class, args);

   // new ShoppingInterface().menu();


	}

}
