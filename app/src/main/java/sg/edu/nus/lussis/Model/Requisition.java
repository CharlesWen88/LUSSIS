package sg.edu.nus.lussis.Model;

import java.util.List;

public class Requisition {

    private String Id;
    private String EmployeeId;
    private String DateTime;
    private String Status;
    private String Remarks;
    private List<RequisitionDetails> RequisitionDetails;

    public Requisition(String id, String employeeId, String dateTime, String status, String remarks, List<RequisitionDetails> requisitionDetails) {
        Id = id;
        EmployeeId = employeeId;
        DateTime = dateTime.substring(0,10);
        this.Status = status;
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
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public List<RequisitionDetails> getRequisitionDetails() {
        return RequisitionDetails;
    }

    public void setRequisitionDetails(List<RequisitionDetails> requisitionDetails) {
        RequisitionDetails = requisitionDetails;
    }
}
