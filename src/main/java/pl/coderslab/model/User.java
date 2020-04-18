package pl.coderslab.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private int userGroupId;

    public User() {

    }

    public User(String username, String email, String password, int userGroupId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userGroupId = userGroupId;
    }

//    public void hashPassword(String password) {
//        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
//    }

    public int getId() { return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    @Override
    public String toString() {
        return "User " +
                "id = " + id +
                ", username = " + username + " " +
                ", email = " + email + " " +
                ", password = " + password + " " +
                ", userGroupId = "  + userGroupId
                ;
    }


}


