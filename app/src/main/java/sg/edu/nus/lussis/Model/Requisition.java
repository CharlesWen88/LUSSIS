package sg.edu.nus.lussis.Model;

import org.json.JSONArray;

import java.util.List;

public class Requisition {

    public final String id;
    public final String date;
    public final String status;
    public final JSONArray requisitionDetail;

    public Requisition(String id, String date, String status, JSONArray requisitionDetail) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.requisitionDetail = requisitionDetail;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public JSONArray getRequisitionDetail() {
        return requisitionDetail;
    }

    @Override
    public String toString() {
        return "Requisition{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", requisitionDetail=" + requisitionDetail +
                '}';
    }
}

