package sg.edu.nus.lussis.model;

import java.util.List;

public class RetrievalItemDTO {
    private int StationeryId;
    private String Description;
    private String Location;
    private String NeededQuantity;
    private String RetrievedQty;
    private List<RetrievalPrepItemDTO> RetrievalPrepItemList;

    public RetrievalItemDTO(int stationeryId, String description, String location, String neededQuantity, String retrievedQty, List<RetrievalPrepItemDTO> retrievalPrepItemList) {
        StationeryId = stationeryId;
        Description = description;
        Location = location;
        NeededQuantity = neededQuantity;
        RetrievedQty = retrievedQty;
        RetrievalPrepItemList = retrievalPrepItemList;
    }

    public int getStationeryId() {
        return StationeryId;
    }

    public void setStationeryId(int stationeryId) {
        StationeryId = stationeryId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getNeededQuantity() {
        return NeededQuantity;
    }

    public void setNeededQuantity(String neededQuantity) {
        NeededQuantity = neededQuantity;
    }

    public String getRetrievedQty() {
        return RetrievedQty;
    }

    public void setRetrievedQty(String retrievedQty) {
        RetrievedQty = retrievedQty;
    }

    public List<RetrievalPrepItemDTO> getRetrievalPrepItemList() {
        return RetrievalPrepItemList;
    }

    public void setRetrievalPrepItemList(List<RetrievalPrepItemDTO> retrievalPrepItemList) {
        RetrievalPrepItemList = retrievalPrepItemList;
    }
}
