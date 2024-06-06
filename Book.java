public class Book {
    private String bookId;
    private String title;
    private String author;
    private String description;
    private String categories;
    private int qty;
    private int booked;

    public Book(String bookId, String title, String author, String description, String categories, int qty, int booked) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.description = description;
        this.categories = categories;
        this.qty = qty;
        this.booked = booked;
    }

    public static Book fromJson(String json) {
        String[] parts = json.replace("{", "").replace("}", "").replace("\"", "").split(",");
        String bookId = parts[0].split(":")[1].trim();
        String title = parts[1].split(":")[1].trim();
        String author = parts[2].split(":")[1].trim();
        String description = parts[3].split(":")[1].trim();
        String categories = parts[4].split(":")[1].trim();
        int qty = Integer.parseInt(parts[5].split(":")[1].trim());
        int booked = Integer.parseInt(parts[6].split(":")[1].trim());
        return new Book(bookId, title, author, description, categories, qty, booked);
    }

    @Override
    public String toString() {
        return "{\"bookId\":\"" + bookId + "\", \"title\":\"" + title + "\", \"author\":\"" + author + "\", \"description\":\"" + description + "\", \"categories\":\"" + categories + "\", \"qty\":\"" + qty + "\", \"booked\":\"" + booked + "\"}";
    }

    // Getters and setters
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }
}
