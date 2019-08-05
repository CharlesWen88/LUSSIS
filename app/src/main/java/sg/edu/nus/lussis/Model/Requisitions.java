package sg.edu.nus.lussis.Model;

import java.util.List;

public class Requisitions {

    private String Id;
    private String EmployeeId;
    private String DateTime;
    private String status;
    private String Remarks;
    private List<RequisitionsDetails> RequisitionDetails;

    public Requisitions(String id, String employeeId, String dateTime, String status, String remarks, List<RequisitionsDetails> requisitionDetails) {
        Id = id;
        EmployeeId = employeeId;
        DateTime = dateTime.substring(0,10);
        this.status = status;
        Remarks = remarks;
        RequisitionDetails = requisitionDetails;
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
        DateTime = dateTime.substring(0,10);
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
