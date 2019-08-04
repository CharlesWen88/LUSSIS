package sg.edu.nus.lussis.Model;

import java.util.List;

public class RequisitionsDTO {

    private LoginDTO LoginDTO;
    private List<Requisitions> Requisitions;

    public RequisitionsDTO(sg.edu.nus.lussis.Model.LoginDTO loginDTO, List<Requisitions> requisitions) {
        LoginDTO = loginDTO;
        Requisitions = requisitions;
    }

    public sg.edu.nus.lussis.Model.LoginDTO getLoginDTO() {
        return LoginDTO;
    }

    public void setLoginDTO(sg.edu.nus.lussis.Model.LoginDTO loginDTO) {
        LoginDTO = loginDTO;
    }

    public List<Requisitions> getRequisitions() {
        return Requisitions;
    }

    public void setRequisitions(List<Requisitions> requisitions) {
        Requisitions = requisitions;
    }
}
