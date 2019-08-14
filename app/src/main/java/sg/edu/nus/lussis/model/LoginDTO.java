package sg.edu.nus.lussis.model;

public class LoginDTO {

    private final String EmployeeId;
    private final String RoleId;
    private final String SessionGuid;
    private final String Name;

    public LoginDTO(String employeeId, String roleId, String sessionGuid, String name) {
        EmployeeId = employeeId;
        RoleId = roleId;
        SessionGuid = sessionGuid;
        Name = name;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public String getRoleId() {
        return RoleId;
    }

    public String getSessionGuid() {
        return SessionGuid;
    }

    public String getName() {
        return Name;
    }
}
