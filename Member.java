public class Member {
    private String memberId;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Member(String memberId, String name, String phone, String email, String address) {
        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public static Member fromJson(String json) {
        String[] parts = json.replace("{", "").replace("}", "").replace("\"", "").split(",");
        String memberId = parts[0].split(":")[1].trim();
        String name = parts[1].split(":")[1].trim();
        String phone = parts[2].split(":")[1].trim();
        String email = parts[3].split(":")[1].trim();
        String address = parts[4].split(":")[1].trim();
        return new Member(memberId, name, phone, email, address);
    }

    @Override
    public String toString() {
        return "{\"memberId\":\"" + memberId + "\", \"name\":\"" + name + "\", \"phone\":\"" + phone + "\", \"email\":\"" + email + "\", \"address\":\"" + address + "\"}";
    }
}
