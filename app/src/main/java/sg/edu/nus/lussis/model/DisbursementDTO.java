package sg.edu.nus.lussis.model;

import java.util.List;

public class DisbursementDTO {
    private String Id;
    private String DeliveredEmployeeId;
    private String ReceivedEmployeeId;
    private String ReceivedEmployeeName;
    private String DepartmentName;
    private boolean AdHoc;
    private String DeliveryDateTime;
    private String CollectionPoint;
    private String Signature;
    private Boolean OnRoute;
    private List<RequisitionDetailDTO> RequisitionDetails;

    public DisbursementDTO(String id, String deliveredEmployeeId, String receivedEmployeeId, String receivedEmployeeName, String departmentName, boolean adHoc, String deliveryDateTime, String collectionPoint, String signature, Boolean onRoute, List<RequisitionDetailDTO> requisitionDetails) {
        Id = id;
        DeliveredEmployeeId = deliveredEmployeeId;
        ReceivedEmployeeId = receivedEmployeeId;
        ReceivedEmployeeName = receivedEmployeeName;
        DepartmentName = departmentName;
        AdHoc = adHoc;
        DeliveryDateTime = deliveryDateTime;
        CollectionPoint = collectionPoint;
        Signature = signature;
        OnRoute = onRoute;
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

    public String getReceivedEmployeeId() {
        return ReceivedEmployeeId;
    }

    public void setReceivedEmployeeId(String receivedEmployeeId) {
        ReceivedEmployeeId = receivedEmployeeId;
    }

    public String getReceivedEmployeeName() {
        return ReceivedEmployeeName;
    }

    public void setReceivedEmployeeName(String receivedEmployeeName) {
        ReceivedEmployeeName = receivedEmployeeName;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public boolean isAdHoc() {
        return AdHoc;
    }

    public void setAdHoc(boolean adHoc) {
        AdHoc = adHoc;
    }

    public String getDeliveryDateTime() {
        return DeliveryDateTime.substring(0,10);
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

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public Boolean getOnRoute() {
        return OnRoute;
    }

    public void setOnRoute(Boolean onRoute) {
        OnRoute = onRoute;
    }

    public List<RequisitionDetailDTO> getRequisitionDetails() {
        return RequisitionDetails;
    }

    public void setRequisitionDetails(List<RequisitionDetailDTO> requisitionDetails) {
        RequisitionDetails = requisitionDetails;
    }
}
