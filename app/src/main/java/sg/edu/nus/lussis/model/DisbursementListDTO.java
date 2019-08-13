package sg.edu.nus.lussis.model;

import java.util.List;

public class DisbursementListDTO {
    private List<DisbursementDTO> Disbursements;

    public DisbursementListDTO(List<DisbursementDTO> disbursements) {
        Disbursements = disbursements;
    }

    public List<DisbursementDTO> getDisbursements() {
        return Disbursements;
    }

    public void setDisbursements(List<DisbursementDTO> disbursements) {
        Disbursements = disbursements;
    }
}
