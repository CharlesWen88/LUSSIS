package sg.edu.nus.lussis.Model;

import java.util.List;

public class Requisitions {

    private int Id;
    private int EmployeeId;
    private String DateTime;
    private String status;
    private String Remarks;
    private List<RequisitionsDetails> RequisitionDetails;

    public Requisitions(int id, int employeeId, String dateTime, String status, String remarks, List<RequisitionsDetails> requisitionDetails) {
        Id = id;
        EmployeeId = employeeId;
        DateTime = dateTime;
        this.status = status;
        Remarks = remarks;
        RequisitionDetails = requisitionDetails;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public List<RequisitionsDetails> getRequisitionDetails() {
        return RequisitionDetails;
    }

    public void setRequisitionDetails(List<RequisitionsDetails> requisitionDetails) {
        RequisitionDetails = requisitionDetails;
    }
}
