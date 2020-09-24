package com.company;

import java.io.*;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Scanner;

class StudentManagerTest {
    public StudentManagerTest() {
    }

    public static void main(String[] args) throws IOException {
        String fileName = "students.txt";

        while(true) {
            while(true) {
                System.out.println("--------欢迎来到学生管理系统--------");
                System.out.println("1 查看所有学生");
                System.out.println("2 添加学生");
                System.out.println("3 删除学生");
                System.out.println("4 修改学生");
                System.out.println("5 退出");
                System.out.println("请输入你的选择：");
                Scanner sc = new Scanner(System.in);
                String choiceString = sc.nextLine();
                switch(choiceString.hashCode()) {
                    case 49:
                        if (choiceString.equals("1")) {
                            findAllStudent(fileName);
                            continue;
                        }
                        break;
                    case 50:
                        if (choiceString.equals("2")) {
                            addStudent(fileName);
                            continue;
                        }
                        break;
                    case 51:
                        if (choiceString.equals("3")) {
                            deleteStudent(fileName);
                            continue;
                        }
                        break;
                    case 52:
                        if (choiceString.equals("4")) {
                            updateStudent(fileName);
                            continue;
                        }
                        break;
                    case 53:
                        if (!choiceString.equals("5")) {
                        }
                }

                System.out.println("谢谢你的使用");
                System.exit(0);
            }
        }
    }

    public static void readData(String fileName, ArrayList<Student> array) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;
        while((line = br.readLine()) != null) {
            String[] datas = line.split(",");
            Student s = new Student();
            s.setId(datas[0]);
            s.setName(datas[1]);
            s.setAge(datas[2]);
            s.setAddress(datas[3]);
            array.add(s);
        }

        br.close();
    }

    public static void writeData(String fileName, ArrayList<Student> array) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

        for(int x = 0; x < array.size(); ++x) {
            Student s = (Student)array.get(x);
            StringBuilder sb = new StringBuilder();
            sb.append(s.getId()).append(",").append(s.getName()).append(",").append(s.getAge()).append(",").append(s.getAddress());
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
        }

        bw.close();
    }

    public static void updateStudent(String fileName) throws IOException {
        ArrayList<Student> array = new ArrayList();
        readData(fileName, array);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要修改的学生的学号：");
        String id = sc.nextLine();
        int index = -1;

        for(int x = 0; x < array.size(); ++x) {
            Student s = (Student)array.get(x);
            if (s.getId().equals(id)) {
                index = x;
                break;
            }
        }

        if (index == -1) {
            System.out.println("不好意思,你要修改的学号对应的学生信息不存在,请回去重新你的选择");
        } else {
            System.out.println("请输入学生新姓名：");
            String name = sc.nextLine();
            System.out.println("请输入学生新年龄：");
            String age = sc.nextLine();
            System.out.println("请输入学生新居住地：");
            String address = sc.nextLine();
            Student s = new Student();
            s.setId(id);
            s.setName(name);
            s.setAge(age);
            s.setAddress(address);
            array.set(index, s);
            writeData(fileName, array);
            System.out.println("修改学生成功");
        }

    }

    public static void deleteStudent(String fileName) throws IOException {
        ArrayList<Student> array = new ArrayList();
        readData(fileName, array);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要删除的学生的学号：");
        String id = sc.nextLine();
        int index = -1;

        for(int x = 0; x < array.size(); ++x) {
            Student s = (Student)array.get(x);
            if (s.getId().equals(id)) {
                index = x;
                break;
            }
        }

        if (index == -1) {
            System.out.println("不好意思,你要删除的学号对应的学生信息不存在,请回去重新你的选择");
        } else {
            array.remove(index);
            writeData(fileName, array);
            System.out.println("删除学生成功");
        }

    }

    public static void addStudent(String fileName) throws IOException {
        ArrayList<Student> array = new ArrayList();
        readData(fileName, array);
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("请输入学生学号：");
            String id = sc.nextLine();
            boolean flag = false;

            for(int x = 0; x < array.size(); ++x) {
                Student s = (Student)array.get(x);
                if (s.getId().equals(id)) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                System.out.println("请输入学生姓名：");
                String name = sc.nextLine();
                System.out.println("请输入学生年龄：");
                String age = sc.nextLine();
                System.out.println("请输入学生居住地：");
                String address = sc.nextLine();
                Student s = new Student();
                s.setId(id);
                s.setName(name);
                s.setAge(age);
                s.setAddress(address);
                array.add(s);
                writeData(fileName, array);
                System.out.println("添加学生成功");
                return;
            }

            System.out.println("你输入的学号已经被占用,请重新输入");
        }
    }

    public static void findAllStudent(String fileName) throws IOException {
        ArrayList<Student> array = new ArrayList();
        readData(fileName, array);
        if (array.size() == 0) {
            System.out.println("不好意思,目前没有学生信息可供查询,请回去重新选择你的操作");
        } else {
            System.out.println("学号\t\t姓名\t年龄\t居住地");

            for(int x = 0; x < array.size(); ++x) {
                Student s = (Student)array.get(x);
                System.out.println(s.getId() + "\t" + s.getName() + "\t" + s.getAge() + "\t" + s.getAddress());
            }

        }
    }
}