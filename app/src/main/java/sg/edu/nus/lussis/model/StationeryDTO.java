package sg.edu.nus.lussis.model;

public class StationeryDTO {
    private String Description;

    public StationeryDTO(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
