package sg.edu.nus.lussis.Model;

public class LoginDTO {

    public final String EmployeeId;
    public final String RoleId;
    public final String SessionGuid;

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
