package sg.edu.nus.lussis.Model;

public class LoginDTO {

    private final String EmployeeId;
    private final String RoleId;
    private final String SessionGuid;

    public LoginDTO(String employeeId, String roleId, String sessionGuid) {
        EmployeeId = employeeId;
        RoleId = roleId;
        SessionGuid = sessionGuid;
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
}
