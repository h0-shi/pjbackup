package com.javaproject.main;

import java.util.Scanner;

import com.javaproject.dao.WorkDAO;
import com.javaproject.function.Function;

public class MainClass {
	static WorkDAO workDAO = new WorkDAO();
	

	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        Function fn = new Function();
	        int choice;

	        do {
	            fn.displayMenu();
	            System.out.print("메뉴를 선택하세요: ");
	            choice = scanner.nextInt();
	            scanner.nextLine();  // Enter key 처리

	            switch (choice) {
	                case 1:
	                	fn.recommand();
	                    break;
	                case 2:
	                    fn.addExercise();
	                    break;
	                case 3:
	                    fn.deleteExercise();
	                    break;
	                case 4:
	                    fn.viewExercises();
	                    break;
	                case 5:
	                	fn.showRecord();
	                	break;
	                case 6:
	                	fn.delRecord();
	                	break;
	                case 7:
	                	fn.fitness();
	                	break;
	                case 0:
	                    System.out.println("프로그램을 종료합니다.");
	                    break;
	                default:
	                    System.out.println("잘못된 선택입니다. 다시 선택하세요.");
	            }

	        } while (choice != 0);

	        scanner.close();
	    }
}