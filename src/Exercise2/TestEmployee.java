/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exercise2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author hieuvannguyen
 */
interface IEmployee {

    int calculate();

    String getName();
}

abstract class Employee implements IEmployee {

    protected String name;
    protected int paymentPerHour;

    public Employee() {
    }

    public Employee(String name, int paymentPerHour) {
        this.name = name;
        this.paymentPerHour = paymentPerHour;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPaymentPerHour() {
        return paymentPerHour;
    }

    public void setPaymentPerHour(int paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }

    public void nhap(Scanner sc) {
        System.out.println("Nhap ten: ");
        this.name = sc.nextLine();
        System.out.println("Nhap so tien tra moi gio");
        this.paymentPerHour = Integer.parseInt(sc.nextLine());
    }

    public void xuat() {
        System.out.println("Ho ten: " + this.getName());
        System.out.println("So tien tra moi gio: " + this.getPaymentPerHour());
    }

    public abstract int calculate();
}

class PartTimeEmployee extends Employee {

    private int workingHours;

    public PartTimeEmployee() {
    }

    public PartTimeEmployee(int workingHours) {
        this.workingHours = workingHours;
    }

    public PartTimeEmployee(int workingHours, String name, int paymentPerHour) {
        super(name, paymentPerHour);
        this.workingHours = workingHours;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPaymentPerHour() {
        return paymentPerHour;
    }

    @Override
    public void setPaymentPerHour(int paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }

    @Override
    public void nhap(Scanner sc) {
        super.nhap(sc);
        System.out.println("Nhap so gio lam: ");
        this.workingHours = Integer.parseInt(sc.nextLine());
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.println("So gio lam: " + this.getWorkingHours());
        System.out.println("Tong luong: " + calculate());
    }

    @Override
    public int calculate() {
        return workingHours * paymentPerHour;
    }
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(String name, int paymentPerHour) {
        super(name, paymentPerHour);
    }

    @Override
    public int calculate() {
        return 8 * paymentPerHour;
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.println("Tong luong: " + calculate());
    }
}

class EmployeeManager {

    private ArrayList<Employee> ds = new ArrayList<>();

    public void addEmployee(Employee employ) {
        ds.add(employ);
    }

    public void showAll() {
        if (ds.isEmpty()) {
            System.out.println("Khong co nhan vien nao !");
        } else {
            for (Employee d : ds) {
                d.xuat();
                System.out.println("--------");
            }
        }
    }

    public void hienThiTongLuongFullTimeEmployee() {
        int tong = 0;
        boolean check = false;
        for (Employee employee : ds) {
            if (employee instanceof FullTimeEmployee) {
                tong += employee.calculate();
                check = true;
            }
        }
        if(!check){
            System.out.println("Khong the hien thi vi khong khong co nhan vien nao");
        }else{
            System.out.println("Show total Salary of FullTime Employee: " + tong);
        }
    }

    public void SortEmployeeByName() {
        Collections.sort(ds, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                String ten1 = e1.getName().substring(e1.getName().lastIndexOf(" ") + 1);
                String ten2 = e2.getName().substring(e2.getName().lastIndexOf(" ") + 1);
                return ten1.compareToIgnoreCase(ten2);
            }
        });
        System.out.println("Da sap xep danh sach theo ten");
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("==========MENU==========");
            System.out.println("1.ADD Employee");
            System.out.println("2.Show all employee");
            System.out.println("3.Show total Salary of FullTime Employee");
            System.out.println("4.Sort Employee by name");
            System.out.println("0.Exit");

            System.out.print("Nhap lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                while (true) {
                    System.out.println("-----Add employee-----");
                    System.out.println("1.PartTime Employee");
                    System.out.println("2.FullTime Employee");
                    System.out.println("0.Exit");

                    System.out.println("Nhap lua chon cua ban: ");
                    int luachon = Integer.parseInt(sc.nextLine());

                    if (luachon == 1) {
                        Employee emp = new PartTimeEmployee();
                        emp.nhap(sc);
                        addEmployee(emp);
                    } else if (luachon == 2) {
                        Employee emp = new FullTimeEmployee();
                        emp.nhap(sc);
                        addEmployee(emp);
                    } else if (luachon == 0) {
                        break;
                    } else {
                        System.out.println("Lua chon khong dung !");
                    }
                }
            } else if (choice == 2) {
                System.out.println("-----Hien thi danh sach-----");
                showAll();
            } else if (choice == 3) {
                System.out.println("-----Show total Salary of FullTime Employee-----");
                hienThiTongLuongFullTimeEmployee();

            } else if (choice == 4) {
                System.out.println("-----Sort Employee by name-----");
                SortEmployeeByName();

            } else if (choice == 0) {
                System.err.println("Exit.");
                break;
            } else {
                System.err.println("Lua chon khong dung !");
            }
        }
    }
}

public class TestEmployee {

    public static void main(String[] args) {
        EmployeeManager employee = new EmployeeManager();
        employee.menu();
    }
}
