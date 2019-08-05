package sg.edu.nus.lussis.Model;

import java.util.List;

public class RequisitionsDTO {

    private LoginDTO LoginDTO;
    private List<Requisition> Requisitions;

    public RequisitionsDTO(LoginDTO loginDTO, List<Requisition> requisitions) {
        LoginDTO = loginDTO;
        Requisitions = requisitions;
    }

    public sg.edu.nus.lussis.Model.LoginDTO getLoginDTO() {
        return LoginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        LoginDTO = loginDTO;
    }

    public List<Requisition> getRequisitions() {
        return Requisitions;
    }

    public void setRequisitions(List<Requisition> requisitions) {
        Requisitions = requisitions;
    }
}
