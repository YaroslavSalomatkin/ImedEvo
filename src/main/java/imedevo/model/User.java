package imedevo.model;


public class User {

    private Integer id;
    private String name;
    private String phone;
    private String email;
    private int birthDate;

    public User(){

    }

    public User(Integer id, String name, String phone, String email, int birthDate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }
}