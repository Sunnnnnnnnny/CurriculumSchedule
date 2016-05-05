package assignment07_test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Classtable {

	// analyse what the user put in;
	static Scanner scan = new Scanner(System.in);
	static String input = scan.nextLine().replaceAll(";", " ");
	static String[] info = input.split(" ");
	static String command = info[0];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Classtable myCourse = new Classtable();
		String command = Classtable.command;
		switch (command) {
		case "Add":
			myCourse.add();
			break;
		case "Remove":
			myCourse.remove();
			break;
		case "Update":
			myCourse.update();
			break;
		case "Find":
			myCourse.find();
			break;
		case "Show":
			myCourse.show();
			break;
		default:
			System.out.println("Error!!!");
		}

	}

	public void add() throws IOException {
		// add courses;

		File myFile = new File("CurriculumSchedule.txt");
		FileWriter writer = new FileWriter(myFile, true);
		FileReader reader = new FileReader(myFile);
		BufferedWriter bw = new BufferedWriter(writer);
		BufferedReader br = new BufferedReader(reader);
		String classInfo = info[1] + ";" + info[2] + ";" + info[3] + ";" + info[4] + ";";
		String s;

		while ((s = br.readLine()) != null) {
			if (s.equals(classInfo)) {
				System.out.println("已存在该课程，无法添加！");
				System.exit(0);
			}
		}
		bw.write(classInfo);
		bw.write("\r\n");
		bw.flush();
		bw.close();
		writer.close();
		System.out.println("已添加到文件中");
		br.close();
		System.exit(0);
	}

	public void remove() throws IOException {
		// remove courses;

		File myFile = new File("CurriculumSchedule.txt");
		FileWriter writer = new FileWriter(myFile, true);
		FileReader reader = new FileReader(myFile);
		BufferedReader br = new BufferedReader(reader);

		ArrayList<String> courses = new ArrayList<String>();
		String classInfo = info[1] + ";" + info[2] + ";" + info[3] + ";" + info[4] + ";";
		String s;

		while ((s = br.readLine()) != null) {
			courses.add(s);
		}

		for (String myCourse : courses) {
			if (myCourse.equals(classInfo)) {
				courses.remove(myCourse);
				break;
			}
		}

		FileWriter neWriter = new FileWriter(myFile);
		BufferedWriter bw = new BufferedWriter(neWriter);

		for (String myCourse : courses) {
			bw.write(myCourse.toString());
			bw.write("\r\n");
		}

		bw.flush();
		bw.close();
		br.close();
		System.out.println("已从文件删除");

		System.exit(0);
	}

	public void update() throws IOException {
		// update courses;

		File myFile = new File("CurriculumSchedule.txt");
		FileWriter writer = new FileWriter(myFile, true);
		FileReader reader = new FileReader(myFile);
		BufferedReader br = new BufferedReader(reader);

		ArrayList<String> courses = new ArrayList<String>();
		String classInfo = info[1] + ";" + info[2] + ";" + info[3] + ";" + info[4] + ";";
		String startInfo = info[1] + ";" + info[2] + ";";
		String s;
		int index = 0;
		boolean toBeRemoved = false;

		while ((s = br.readLine()) != null) {
			courses.add(s);
		}

		for (String myCourse : courses) {
			if (myCourse.startsWith(startInfo)) {
				toBeRemoved = true;
				index = courses.indexOf(myCourse);
			}
		}

		if (toBeRemoved) {
			courses.remove(index);
			courses.add(classInfo);
		}

		FileWriter neWriter = new FileWriter(myFile);
		BufferedWriter bw = new BufferedWriter(neWriter);

		for (String myCourse : courses) {
			bw.write(myCourse.toString());
			bw.write("\r\n");
		}

		bw.close();
		br.close();
		System.out.println("已更新文件");

		System.exit(0);
	}

	public void find() throws IOException {
		// find courses;

		File myFile = new File("CurriculumSchedule.txt");
		FileWriter writer = new FileWriter(myFile, true);
		FileReader reader = new FileReader(myFile);
		BufferedWriter bw = new BufferedWriter(writer);
		BufferedReader br = new BufferedReader(reader);
		boolean existance = false;

		for (String tmp = null; (tmp = br.readLine()) != null; tmp = null) {
			String[] mytmp = tmp.split(";");
			if (mytmp[0].equals(info[1]) && mytmp[1].equals(info[2])) {
				System.out.println(mytmp[2] + ";" + mytmp[3]);
				existance = true;
				break;
			} else {
			}

		}
		if (!existance) {
			System.out.println("没有对应课程！");
		}

		bw.close();
		br.close();

		System.exit(0);
	}

	public void show() throws IOException {
		// show all the courses;

		File myFile = new File("CurriculumSchedule.txt");
		FileWriter writer = new FileWriter(myFile, true);
		FileReader reader = new FileReader(myFile);
		BufferedWriter bw = new BufferedWriter(writer);
		BufferedReader br = new BufferedReader(reader);

		for (String tmp = null; (tmp = br.readLine()) != null; tmp = null) {
			System.out.println(tmp);
		}

		bw.close();
		br.close();

		System.exit(0);
	}

}
