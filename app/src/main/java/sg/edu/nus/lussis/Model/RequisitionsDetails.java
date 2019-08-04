package sg.edu.nus.lussis.Model;

import java.util.List;

public class RequisitionsDetails {

    private int Id;
    private int RequisitionId;
    private int DisbursementId;
    private int StationeryId;
    private int QuantityOrdered;
    private int QuantityDelivered;
    private String Status;
    private Stationery Stationery;

    public RequisitionsDetails(int id, int requisitionId, int disbursementId, int stationeryId, int quantityOrdered, int quantityDelivered, String status, Stationery stationery) {
        Id = id;
        RequisitionId = requisitionId;
        DisbursementId = disbursementId;
        StationeryId = stationeryId;
        QuantityOrdered = quantityOrdered;
        QuantityDelivered = quantityDelivered;
        Status = status;
        Stationery = stationery;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getRequisitionId() {
        return RequisitionId;
    }

    public void setRequisitionId(int requisitionId) {
        RequisitionId = requisitionId;
    }

    public int getDisbursementId() {
        return DisbursementId;
    }

    public void setDisbursementId(int disbursementId) {
        DisbursementId = disbursementId;
    }

    public int getStationeryId() {
        return StationeryId;
    }

    public void setStationeryId(int stationeryId) {
        StationeryId = stationeryId;
    }

    public int getQuantityOrdered() {
        return QuantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        QuantityOrdered = quantityOrdered;
    }

    public int getQuantityDelivered() {
        return QuantityDelivered;
    }

    public void setQuantityDelivered(int quantityDelivered) {
        QuantityDelivered = quantityDelivered;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Stationery getStationery() {
        return Stationery;
    }

    public void setStationery(Stationery stationery) {
        Stationery = stationery;
    }
}
