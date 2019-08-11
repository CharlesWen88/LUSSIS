package sg.edu.nus.lussis.model;

public class RetrievalPrepItemDTO {
    private RequisitionDetailDTO RequisitionDetail;

    public RetrievalPrepItemDTO(RequisitionDetailDTO requisitionDetail) {
        RequisitionDetail = requisitionDetail;
    }

    public RequisitionDetailDTO getRequisitionDetail() {
        return RequisitionDetail;
    }

    public void setRequisitionDetail(RequisitionDetailDTO requisitionDetail) {
        RequisitionDetail = requisitionDetail;
    }
}
