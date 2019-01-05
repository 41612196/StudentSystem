package main.vo;

/**
 * Create by pengweijie on 2018/11/21
 */
public class User {
    private String  username;//统一用学号或教师id或工号
    private String password;
    private String superuser;

    public User() {
    }

    public User(String username, String password, String superuser) {
        this.username = username;
        this.password = password;
        this.superuser = superuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSuperuser() {
        return superuser;
    }

    public void setSuperuser(String superuser) {
        this.superuser = superuser;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", superuser='" + superuser + '\'' +
                '}';
    }
}
