package sg.edu.nus.lussis.model;

import java.util.List;

public class RetrievalDTO {
    private LoginDTO LoginDTO;
    private String RetrievalDate;
    private int AdHocRetrievalId;
    private List<RetrievalItemDTO> RetrievalItem;

    public RetrievalDTO(LoginDTO loginDTO, String retrievalDate, int adHocRetrievalId, List<RetrievalItemDTO> retrievalItem) {
        LoginDTO = loginDTO;
        RetrievalDate = retrievalDate;
        AdHocRetrievalId = adHocRetrievalId;
        RetrievalItem = retrievalItem;
    }

    public LoginDTO getLoginDTO() {
        return LoginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        LoginDTO = loginDTO;
    }

    public String getRetrievalDate() {
        return RetrievalDate;
    }

    public void setRetrievalDate(String retrievalDate) {
        RetrievalDate = retrievalDate;
    }

    public int getAdHocRetrievalId() {
        return AdHocRetrievalId;
    }

    public void setAdHocRetrievalId(int adHocRetrievalId) {
        AdHocRetrievalId = adHocRetrievalId;
    }

    public List<RetrievalItemDTO> getRetrievalItem() {
        return RetrievalItem;
    }

    public void setRetrievalItem(List<RetrievalItemDTO> retrievalItem) {
        RetrievalItem = retrievalItem;
    }
}
