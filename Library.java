import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Transaction> transactions;
    private LibraryDatabase database;

    public Library() {
        database = new LibraryDatabase();
        books = database.loadBooks();
        members = database.loadMembers();
        transactions = database.loadTransactions();
    }

    public void addBook(Scanner scanner) {
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Categories: ");
        String categories = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = scanner.nextInt();
        scanner.nextLine();  // consume newline

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("A book with this title already exists.");
                return;
            }
        }

        Book book = new Book(bookId, title, author, description, categories, qty, 0);
        books.add(book);
        database.saveBooks(books);
        System.out.println("Book added successfully.");
    }

    public void addMember(Scanner scanner) {
        System.out.print("Enter Member Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                System.out.println("A member with this name already exists.");
                return;
            }
        }

        Member member = new Member(memberId, name, phone, email, address);
        members.add(member);
        database.saveMembers(members);
        System.out.println("Member added successfully.");
    }

    public void borrowBook(Scanner scanner) {
        System.out.print("Enter Transaction ID: ");
        String transactionId = scanner.nextLine();
        System.out.print("Enter Member Name: ");
        String memberName = scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String bookTitle = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                if (book.getBooked() < book.getQty()) {
                    book.setBooked(book.getBooked() + 1);
                    Transaction transaction = new Transaction(transactionId, new Date(), memberName, book.getTitle(), 1);
                    transactions.add(transaction);
                    database.saveBooks(books);
                    database.saveTransactions(transactions);
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("No available copies of the book.");
                }
                return;
            }
        }
        System.out.println("Book title not found.");
    }

    public void returnBook(Scanner scanner) {
        System.out.print("Enter Transaction ID: ");
        String transactionId = scanner.nextLine();

        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(transactionId) && transaction.getStatus() == 1) {
                for (Book book : books) {
                    if (book.getTitle().equalsIgnoreCase(transaction.getBookTitle())) {
                        book.setBooked(book.getBooked() - 1);
                        transaction.setStatus(2);
                        database.saveBooks(books);
                        database.saveTransactions(transactions);
                        System.out.println("Book returned successfully.");
                        return;
                    }
                }
            }
        }
        System.out.println("Transaction ID not found or book already returned.");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        System.out.println("ID | Title | Author | Description | Categories | Qty | Booked");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void viewMembers() {
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }
        System.out.println("ID | Name | Phone | Email | Address");
        for (Member member : members) {
            System.out.println(member);
        }
    }
}
