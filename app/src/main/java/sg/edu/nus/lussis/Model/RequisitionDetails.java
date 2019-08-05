package sg.edu.nus.lussis.Model;

import java.util.List;

public class RequisitionDetails {

    private String Id;
    private String RequisitionId;
    private String DisbursementId;
    private String StationeryId;
    private String QuantityOrdered;
    private String QuantityDelivered;
    private String Status;
    private Stationery Stationery;

    public RequisitionDetails(String id, String requisitionId, String disbursementId, String stationeryId, String quantityOrdered, String quantityDelivered, String status, sg.edu.nus.lussis.Model.Stationery stationery) {
        Id = id;
        RequisitionId = requisitionId;
        DisbursementId = disbursementId;
        StationeryId = stationeryId;
        QuantityOrdered = quantityOrdered;
        QuantityDelivered = quantityDelivered;
        Status = status;
        Stationery = stationery;
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

    public String getQuantityDelivered() {
        return QuantityDelivered;
    }

    public void setQuantityDelivered(String quantityDelivered) {
        QuantityDelivered = quantityDelivered;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public sg.edu.nus.lussis.Model.Stationery getStationery() {
        return Stationery;
    }

    public void setStationery(sg.edu.nus.lussis.Model.Stationery stationery) {
        Stationery = stationery;
    }
}
