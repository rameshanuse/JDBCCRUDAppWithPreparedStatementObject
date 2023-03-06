package in.ineuron.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;

public class TestApp {

	public static void main(String[] args) throws Exception {
		System.out.println("WELCOME TO STUDENT DATA MANAGEMENT APP");
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. EXIT");
			System.out.print("ENTER UR CHOICE, PRESS[1/2/3/4/5]::  ");
			String option = br.readLine();

			switch (option) {
			case "1":
				insertOperation();
				break;
			case "2":
				selectOperation();
				break;
			case "3":
				updateOperation();
				break;
			case "4":
				deleteOperation();
				break;
			case "5":
				System.out.println("******* Thanks for using the application *****");
				System.exit(0);
			default:
				System.out.println("Invalid option plz try agin with valid options....");
				break;
			}

		}

	}
	private static void updateOperation() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter student id to be updated :: ");
		String sid = br.readLine();

		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student student = studentService.searchStudent(Integer.parseInt(sid));

		if (student != null) {
			Student newStudent = new Student();

			System.out.print("Student id is :: " + student.getSid());
			newStudent.setSid(student.getSid());

			System.out.println();

			System.out.print("Student oldName is :: " + student.getSname() + " Enter newName :: ");
			String newName = br.readLine();
			if (newName.equals("") || newName == "") {
				newStudent.setSname(student.getSname());
			} else {
				newStudent.setSname(newName);
			}

			System.out.print("Student oldAge is :: " + student.getSage() + " Enter newAge :: ");
			String newAge = br.readLine();
			if (newAge.equals("") || newAge == "") {
				newStudent.setSage(student.getSage());
			} else {
				newStudent.setSage(Integer.parseInt(newAge));
			}

			System.out.print("Student oldAddress is :: " + student.getSaddress() + " Enter newAddress :: ");
			String newAddress = br.readLine();
			if (newAddress.equals("") || newAddress == "") {
				newStudent.setSaddress(student.getSaddress());
			} else {
				newStudent.setSaddress(newAddress);
			}

			String status = studentService.updateStudent(newStudent);
			if (status.equalsIgnoreCase("success")) {
				System.out.println("Record updated successfully...");
			} else {
				System.out.println("Record updation failed...");
			}

		} else {
			System.out.println(
					"Student record is not available for given id :: " + student.getSid() + " for updation...");
		}
	}

	private static void selectOperation() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the id of student :: ");
		int sid = scanner.nextInt();

		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student std = studentService.searchStudent(sid);

		if (std != null) {
			System.out.println("SId\tSNAME\tSAGE\tSADDRESS");
			System.out.println(std.getSid() + "\t" + std.getSname() + "\t" + std.getSage() + "\t" + std.getSaddress());
		} else {
			System.out.println("Record not found for the given record :: " + sid);
		}

	}

	private static void insertOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the name of student :: ");
		String sname = scanner.next();

		System.out.print("Enter the age of student :: ");
		int sage = scanner.nextInt();

		System.out.print("Enter the address of studnt :: ");
		String saddress = scanner.next();

		String msg = studentService.addStudent(sname, sage, saddress);

		if (msg.equalsIgnoreCase("success")) {
			System.out.println("Record inserted succesfully...");
		} else {
			System.out.println("Record insertion faild...");
		}

	}

	private static void deleteOperation() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the student id to be deleted :: ");
		int sid = scanner.nextInt();

		IStudentService studentService = StudentServiceFactory.getStudentService();
		String msg = studentService.deleteStudent(sid);

		if (msg.equalsIgnoreCase("success")) {
			System.out.println("Record deleted successfully...");
		} else if (msg.equalsIgnoreCase("not found")) {
			System.out.println("Record not availabe for the given id :: " + sid);
		} else {
			System.out.println("Record deletion failed...");
		}

	}

}
