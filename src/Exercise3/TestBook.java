package Exercise3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

interface IBook {

    double Calculate_Total();
}
 
abstract class Book {

    protected String bookID;
    protected String publisher;
    protected Date entryDate;
    protected double unitPrice;
    protected double quantityl;

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getQuantityl() {
        return quantityl;
    }

    public void setQuantityl(double quantityl) {
        this.quantityl = quantityl;
    }

    public void Input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap Ma Sach: ");
        bookID = sc.nextLine();

        System.out.print("Nhap Nha Xuat Ban: ");
        publisher = sc.nextLine();

        System.out.print("Nhap Gia Don Vi: ");
        unitPrice = sc.nextDouble();

        System.out.print("Nhap So Luong: ");
        quantityl = sc.nextDouble();

        sc.nextLine();  // clear buffer

        System.out.print("Nhap Ngay Nhap Sach (dd/MM/yyyy): ");
        String inputDate = sc.nextLine();
        try {
            this.entryDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
        } catch (Exception e) {
            System.err.println("Dinh dang khong hop le! Su dung ngay hien tai!");
            this.entryDate = new Date();
        }
    }

    public void Output() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ma sach: " + bookID);
        System.out.println("Nha xuat ban: " + publisher);
        System.out.println("Gia don vi: " + unitPrice);
        System.out.println("So luong: " + quantityl);
        System.out.println("Ngay nhap: " + sdf.format(entryDate));
    }

    public abstract double CalculateTotal();
}

class TextBook extends Book {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void Input() {
        super.Input();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap Trang Thai Sach (new/old): ");
        status = sc.nextLine();
    }

    @Override
    public void Output() {
        super.Output();
        System.out.println("Trang Thai Sach: " + status);
    }

    public double CalculateTotal() {
        if ("new".equalsIgnoreCase(status)) {
            return quantityl * unitPrice;
        } else if ("old".equalsIgnoreCase(status)) {
            return quantityl * unitPrice * 0.5;
        } else {
            return 0;
        }
    }
}

class RefernceBook extends Book {

    private double tax;

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    @Override
    public void Input() {
        super.Input();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap Thue Sach: ");
        tax = sc.nextDouble();
    }

    @Override
    public void Output() {
        super.Output();
        System.out.println("Thue Sach: " + tax);
    }

    @Override
    public double CalculateTotal() {
        return quantityl * unitPrice + tax;
    }
}
class BookList {
     private ArrayList<Book> books = new ArrayList<>();

    public void AddNew(Book book) {
        books.add(book);
        System.out.println("Da them sach vao danh sach.");
    }

    public void Update(String bookID) {
        Book book = FindBook(bookID);
        if (book != null) {
            book.Input();
            System.out.println("Cap nhat thong tin thanh cong.");
        } else {
            System.out.println("Khong tim thay sach voi Ma Sach: " + bookID);
        }
    }

    public Book FindBook(String bookID) {
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                return book;
            }
        }
        return null;
    }

    public void Delete(String bookID) {
        Book book = FindBook(bookID);
        if (book != null) {
            books.remove(book);
            System.out.println("Da xoa sach khoi danh sach.");
        } else {
            System.out.println("Khong tim thay sach voi Ma Sach: " + bookID);
        }
    }

    public double CalculateTotalAmount() {
        double total = 0;
        for (Book book : books) {
            total += book.CalculateTotal();
        }
        return total;
    }

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=== MENU ===");
            System.out.println("1. Them sach moi");
            System.out.println("2. Cap nhat thong tin sach");
            System.out.println("3. Tim sach theo Ma Sach");
            System.out.println("4. Xoa sach theo Ma Sach");
            System.out.println("5. Tinh tong gia tri tat ca cac sach");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            choice = sc.nextInt();
            sc.nextLine();  // Clear buffer

            switch (choice) {
                case 1:
                    System.out.println("Nhap thong tin sach moi:");
                    System.out.print("Loai sach (1. TextBook, 2. ReferenceBook): ");
                    int type = sc.nextInt();
                    sc.nextLine();  // Clear buffer

                    Book book;
                    if (type == 1) {
                        book = new TextBook();
                    } else if (type == 2) {
                        book = new RefernceBook();
                    } else {
                        System.out.println("Loai sach khong hop le.");
                        break;
                    }
                    book.Input();
                    AddNew(book);
                    break;

                case 2:
                    System.out.print("Nhap Ma Sach can cap nhat: ");
                    String updateID = sc.nextLine();
                    Update(updateID);
                    break;

                case 3:
                    System.out.print("Nhap Ma Sach can tim: ");
                    String findID = sc.nextLine();
                    Book foundBook = FindBook(findID);
                    if (foundBook != null) {
                        foundBook.Output();
                    } else {
                        System.out.println("Khong tim thay sach voi Ma Sach: " + findID);
                    }
                    break;

                case 4:
                    System.out.print("Nhap Ma Sach can xoa: ");
                    String deleteID = sc.nextLine();
                    Delete(deleteID);
                    break;

                case 5:
                    double totalAmount = CalculateTotalAmount();
                    System.out.println("Tong gia tri cua tat ca sach: " + totalAmount);
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;

                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while (choice != 0);
    }

    
}

public class TestBook {
    public static void main(String[] args) {
        BookList manager = new BookList();
        manager.showMenu();
    }
}
