package sg.edu.nus.lussis.model;

public class RequisitionDetailDTO {
    private String Id;
    private String RequisitionId;
    private String DisbursementId;
    private String StationeryId;
    private String QuantityOrdered;
    private String QuantityRetrieved;
    private String QuantityDelivered;
    private String TempQty;
    private String Status;
    private StationeryDTO Stationery;
    private DisbursementDTO Disbursement;
    private RequisitionDTO Requisition;

    public RequisitionDetailDTO(String id, String requisitionId, String disbursementId, String stationeryId, String quantityOrdered, String quantityRetrieved, String quantityDelivered, String tempQty, String status, StationeryDTO stationery, DisbursementDTO disbursement, RequisitionDTO requisition) {
        Id = id;
        RequisitionId = requisitionId;
        DisbursementId = disbursementId;
        StationeryId = stationeryId;
        QuantityOrdered = quantityOrdered;
        QuantityRetrieved = quantityRetrieved;
        QuantityDelivered = quantityDelivered;
        TempQty = tempQty;
        Status = status;
        Stationery = stationery;
        Disbursement = disbursement;
        Requisition = requisition;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRequisitionId() {
        return RequisitionId;
    }

    public void setRequisitionId(String requisitionId) {
        RequisitionId = requisitionId;
    }

    public String getDisbursementId() {
        return DisbursementId;
    }

    public void setDisbursementId(String disbursementId) {
        DisbursementId = disbursementId;
    }

    public String getStationeryId() {
        return StationeryId;
    }

    public void setStationeryId(String stationeryId) {
        StationeryId = stationeryId;
    }

    public String getQuantityOrdered() {
        return QuantityOrdered;
    }

    public void setQuantityOrdered(String quantityOrdered) {
        QuantityOrdered = quantityOrdered;
    }

    public String getQuantityRetrieved() {
        return QuantityRetrieved;
    }

    public void setQuantityRetrieved(String quantityRetrieved) {
        QuantityRetrieved = quantityRetrieved;
    }

    public String getQuantityDelivered() {
        return QuantityDelivered;
    }

    public void setQuantityDelivered(String quantityDelivered) {
        QuantityDelivered = quantityDelivered;
    }

    public String getTempQty() {
        return TempQty;
    }

    public void setTempQty(String tempQty) {
        TempQty = tempQty;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public StationeryDTO getStationery() {
        return Stationery;
    }

    public void setStationery(StationeryDTO stationery) {
        Stationery = stationery;
    }

    public DisbursementDTO getDisbursement() {
        return Disbursement;
    }

    public void setDisbursement(DisbursementDTO disbursement) {
        Disbursement = disbursement;
    }

    public RequisitionDTO getRequisition() {
        return Requisition;
    }

    public void setRequisition(RequisitionDTO requisition) {
        Requisition = requisition;
    }
}
