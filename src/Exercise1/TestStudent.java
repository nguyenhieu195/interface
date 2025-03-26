/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exercise1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author hieuvannguyen
 */
interface IStudent {

    double CalculateAverage();
}

class Student implements IStudent {

    private String fullName;
    private static int idCount = 1;
    private int id;
    private Date dateOfBirth;
    private String phoneNo;

    private double chemisScore;
    private double mathsScore;
    private double physScore;

    public Student() {
        this.id = idCount++;
    }

    public Student(String fullName, int id, Date dateOfBirth, String phoneNo) {
        this.fullName = fullName;
        this.id = idCount++;
        this.dateOfBirth = dateOfBirth;
        this.phoneNo = phoneNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void input(Scanner sc) {
        System.out.println("- Nhap full name: ");
        this.fullName = sc.nextLine();
//        System.out.println("- Nhap id: ");
//        this.id = Integer.parseInt(sc.nextLine());
        System.out.println("- Nhap Date of birth(dd/MM/yyyy): ");
        String dateInput = sc.nextLine();
        try {
            this.dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(dateInput);
        } catch (Exception e) {
            System.err.println("Dinh dang khong hop le !");
        }
        System.out.println("- Nhap phone no: ");
        this.phoneNo = sc.nextLine();
        System.out.println("- Nhap diem toan: ");
        this.mathsScore = Double.parseDouble(sc.nextLine());
        System.out.println("- Nhap diem hoa: ");
        this.chemisScore = Double.parseDouble(sc.nextLine());
        System.out.println("- Nhap diem ly: ");
        this.physScore = Double.parseDouble(sc.nextLine());
    }

    public void output() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Full name: " + this.getFullName());
        System.out.println("ID: " + this.getId());
        System.out.println("Date of birth: " + sdf.format(getDateOfBirth()));
        System.out.println("Phone no: " + this.getPhoneNo());
        System.out.println("Average: " + CalculateAverage());
    }

    public double chemisMark() {
        return chemisScore;
    }

    public double mathsMark() {
        return mathsScore;
    }

    public double physMark() {
        return physScore;
    }

    public double CalculateAverage() {
        return (chemisScore + mathsScore + physScore) / 3;
    }
}

class ManageStudent {

    ArrayList<Student> ds = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void insert(Student sv) {
        ds.add(sv);
    }

    public void showAll() {
        if (ds.isEmpty()) {
            System.out.println("Khong co sinh vien nao !");
        } else {
            for (Student d : ds) {
                d.output();
                System.out.println("-----------------");
            }
        }
    }

    public void seachID(int id) {
        boolean check = false;
        for (Student d : ds) {
            if (d.getId() == id) {
                d.output();
                check = true;
                break;
            }
        }
        if (!check) {
            System.err.println("Khong tim thay Student co id: " + id);
        }
    }

    public void updateID(int id) {
        boolean check = false;
        for (Student d : ds) {
            if (d.getId() == id) {
                System.out.println("Nhap thong tin moi cho Student: ");
                d.input(sc);
                check = true;
                break;
            }
        }
        if (!check) {
            System.err.println("Khong tim thay Student co id: " + id);
        }
    }

    public void menu() {
        OUTER:
        while (true) {
            System.out.println("========QUAN LY SINH VIEN========");
            System.out.println("1.Them Student");
            System.out.println("2.Show all Student");
            System.out.println("3.Search Student by ID");
            System.out.println("4.Update Student");
            System.out.println("0.EXIT.");
            System.out.println("Enter your selection");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("---Them Student---");
                    Student studen = new Student();
                    studen.input(sc);
                    insert(studen);
                    break;
                case 2:
                    System.out.println("---Show all Student---");
                    showAll();
                    break;
                case 3: {
                    System.out.println("---Search Student by ID---");
                    System.out.println("Enter the student id you want to find: ");
                    int id = Integer.parseInt(sc.nextLine());
                    seachID(id);
                    break;
                }
                case 4: {
                    System.out.println("---Update Student---");
                    System.out.println("Enter student id to update information: ");
                    int id = Integer.parseInt(sc.nextLine());
                    updateID(id);
                    break;
                }
                case 0:
                    System.out.println("EXIT.");
                    break OUTER;
                default:
                    System.err.println("Wrong choice !");
                    break;
            }
        }
    }
}

public class TestStudent {

    public static void main(String[] args) {
        ManageStudent m = new ManageStudent();
        m.menu();
    }
}
