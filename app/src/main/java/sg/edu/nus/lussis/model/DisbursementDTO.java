package sg.edu.nus.lussis.model;

import java.util.List;

public class DisbursementDTO {
    private String Id;
    private String DeliveredEmployeeId;
    private String ReveivedEmployeeId;
    private boolean AdHoc;
    private String DeliveryDateTime;
    private String CollectionPoint;
    private EmployeeDTO Employee1;
    private List<RequisitionDetailDTO> RequisitionDetails;

    public DisbursementDTO(String id, String deliveredEmployeeId, String reveivedEmployeeId, boolean adHoc, String deliveryDateTime, String collectionPoint, EmployeeDTO employee1, List<RequisitionDetailDTO> requisitionDetails) {
        Id = id;
        DeliveredEmployeeId = deliveredEmployeeId;
        ReveivedEmployeeId = reveivedEmployeeId;
        AdHoc = adHoc;
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

    public EmployeeDTO getEmployee1() {
        return Employee1;
    }

    public void setEmployee1(EmployeeDTO employee1) {
        Employee1 = employee1;
    }

    public List<RequisitionDetailDTO> getRequisitionDetails() {
        return RequisitionDetails;
    }

    public void setRequisitionDetails(List<RequisitionDetailDTO> requisitionDetails) {
        RequisitionDetails = requisitionDetails;
    }
}
