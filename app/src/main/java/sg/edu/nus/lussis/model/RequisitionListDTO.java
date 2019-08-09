package sg.edu.nus.lussis.model;

import java.util.List;

public class RequisitionListDTO {
    List<RequisitionDTO> Requisitions;

    public RequisitionListDTO(List<RequisitionDTO> requisitions) {
        Requisitions = requisitions;
    }

    public List<RequisitionDTO> getRequisitions() {
        return Requisitions;
    }

    public void setRequisitions(List<RequisitionDTO> requisitions) {
        Requisitions = requisitions;
    }
}
