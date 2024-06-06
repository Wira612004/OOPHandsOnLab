import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDatabase {
    private static final String BOOKS_FILE = "books.json";
    private static final String MEMBERS_FILE = "members.json";
    private static final String TRANSACTIONS_FILE = "transactions.json";

    public List<Book> loadBooks() {
        return loadBooksFromFile(BOOKS_FILE);
    }

    public List<Member> loadMembers() {
        return loadMembersFromFile(MEMBERS_FILE);
    }

    public List<Transaction> loadTransactions() {
        return loadTransactionsFromFile(TRANSACTIONS_FILE);
    }

    public void saveBooks(List<Book> books) {
        saveToFile(BOOKS_FILE, books);
    }

    public void saveMembers(List<Member> members) {
        saveToFile(MEMBERS_FILE, members);
    }

    public void saveTransactions(List<Transaction> transactions) {
        saveToFile(TRANSACTIONS_FILE, transactions);
    }

    private List<Book> loadBooksFromFile(String fileName) {
        List<Book> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Book.fromJson(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + fileName + ": " + e.getMessage());
        }
        return list;
    }

    private List<Member> loadMembersFromFile(String fileName) {
        List<Member> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Member.fromJson(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + fileName + ": " + e.getMessage());
        }
        return list;
    }

    private List<Transaction> loadTransactionsFromFile(String fileName) {
        List<Transaction> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Transaction.fromJson(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + fileName + ": " + e.getMessage());
        }
        return list;
    }

    private void saveToFile(String fileName, List<?> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Object item : data) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file " + fileName + ": " + e.getMessage());
        }
    }
}
