package sg.edu.nus.lussis.model;

import java.util.List;

public class RequisitionDTO {
    private String Id;
    private String EmployeeId;
    private String DateTime;
    private String Status;
    private String Remarks;
    private List<RequisitionDetailDTO> RequisitionDetails;
    private EmployeeDTO Employee;

    public RequisitionDTO(String id, String employeeId, String dateTime, String status, String remarks, List<RequisitionDetailDTO> requisitionDetails, EmployeeDTO employee) {
        Id = id;
        EmployeeId = employeeId;
        DateTime = dateTime;
        Status = status;
        Remarks = remarks;
        RequisitionDetails = requisitionDetails;
        Employee = employee;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getDateTime() {
        return DateTime.substring(0,10);
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public List<RequisitionDetailDTO> getRequisitionDetails() {
        return RequisitionDetails;
    }

    public void setRequisitionDetails(List<RequisitionDetailDTO> requisitionDetails) {
        RequisitionDetails = requisitionDetails;
    }

    public EmployeeDTO getEmployee() {
        return Employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        Employee = employee;
    }
}
