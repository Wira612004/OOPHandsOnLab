import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String id;
    private Date date;
    private String memberName;
    private String bookTitle;
    private int status; // 1 = borrowed, 2 = returned

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public Transaction(String id, Date date, String memberName, String bookTitle, int status) {
        this.id = id;
        this.date = date;
        this.memberName = memberName;
        this.bookTitle = bookTitle;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static Transaction fromJson(String json) {
        String[] parts = json.replace("{", "").replace("}", "").replace("\"", "").split(",");
        String id = parts[0].split(":")[1].trim();
        Date date = null;
        try {
            date = dateFormat.parse(parts[1].split(":")[1].trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String memberName = parts[2].split(":")[1].trim();
        String bookTitle = parts[3].split(":")[1].trim();
        int status = Integer.parseInt(parts[4].split(":")[1].trim());
        return new Transaction(id, date, memberName, bookTitle, status);
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\", \"date\":\"" + dateFormat.format(date) + "\", \"memberName\":\"" + memberName + "\", \"bookTitle\":\"" + bookTitle + "\", \"status\":\"" + status + "\"}";
    }
}
