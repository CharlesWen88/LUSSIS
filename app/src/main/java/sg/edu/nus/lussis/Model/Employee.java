package sg.edu.nus.lussis.Model;

public class Employee {
    private String Id;
    private String Name;
    private String Email;
    private String Username;
    private String Password;
    private String Title;
    private String DepartmentId;
    private String RoleId;
    private byte[] Image;

    public Employee(String id, String name, String email, String username, String password, String title, String departmentId, String roleId, byte[] image) {
        Id = id;
        Name = name;
        Email = email;
        Username = username;
        Password = password;
        Title = title;
        DepartmentId = departmentId;
        RoleId = roleId;
        Image = image;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(String departmentId) {
        DepartmentId = departmentId;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }
}
