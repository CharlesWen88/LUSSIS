package sg.edu.nus.lussis.Model;

public class RequisitionDetail {

    public final String id;
    public final String stationeryName;
    public final String quantity;
    public final String status;

    public RequisitionDetail(String id, String stationeryName, String quantity, String status) {
        this.id = id;
        this.stationeryName = stationeryName;
        this.quantity = quantity;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getStationeryName() {
        return stationeryName;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "RequisitionDetail{" +
                "id='" + id + '\'' +
                ", stationeryName='" + stationeryName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
