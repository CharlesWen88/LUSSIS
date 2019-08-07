package sg.edu.nus.lussis.model;

import java.util.List;

public class Disbursement {
    private String Id;
    private String DeliveredEmployeeId;
    private String ReveivedEmployeeId;
    private boolean AdHoc;
    private String DeliveryDateTime;
    private String CollectionPoint;
    private Employee Employee1;
    private List<RequisitionDetails> RequisitionDetails;

    public Disbursement(String id, String deliveredEmployeeId, String reveivedEmployeeId, boolean adhoc, String deliveryDateTime, String collectionPoint, Employee employee1, List<sg.edu.nus.lussis.model.RequisitionDetails> requisitionDetails) {
        Id = id;
        DeliveredEmployeeId = deliveredEmployeeId;
        ReveivedEmployeeId = reveivedEmployeeId;
        AdHoc = adhoc;
        DeliveryDateTime = deliveryDateTime;
        CollectionPoint = collectionPoint;
        Employee1 = employee1;
        RequisitionDetails = requisitionDetails;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDeliveredEmployeeId() {
        return DeliveredEmployeeId;
    }

    public void setDeliveredEmployeeId(String deliveredEmployeeId) {
        DeliveredEmployeeId = deliveredEmployeeId;
    }

    public String getReveivedEmployeeId() {
        return ReveivedEmployeeId;
    }

    public void setReveivedEmployeeId(String reveivedEmployeeId) {
        ReveivedEmployeeId = reveivedEmployeeId;
    }

    public boolean isAdHoc() {
        return AdHoc;
    }

    public void setAdHoc(boolean adHoc) {
        AdHoc = adHoc;
    }

    public String getDeliveryDateTime() {
        return DeliveryDateTime;
    }

    public void setDeliveryDateTime(String deliveryDateTime) {
        DeliveryDateTime = deliveryDateTime;
    }

    public String getCollectionPoint() {
        return CollectionPoint;
    }

    public void setCollectionPoint(String collectionPoint) {
        CollectionPoint = collectionPoint;
    }

    public Employee getEmployee1() {
        return Employee1;
    }

    public void setEmployee1(Employee employee1) {
        Employee1 = employee1;
    }

    public List<sg.edu.nus.lussis.model.RequisitionDetails> getRequisitionDetails() {
        return RequisitionDetails;
    }

    public void setRequisitionDetails(List<sg.edu.nus.lussis.model.RequisitionDetails> requisitionDetails) {
        RequisitionDetails = requisitionDetails;
    }
}
