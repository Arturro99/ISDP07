package pl.lodz.p.it.isdp.wm.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.lodz.p.it.isdp.wm.dto.ContractorDTO;
import pl.lodz.p.it.isdp.wm.exception.AppBaseException;
import pl.lodz.p.it.isdp.wm.web.utils.ContextUtils;

@Named(value = "resetContractorPageBean")
@RequestScoped
public class ResetContractorPageBean {

    @Inject
    private ContractorControllerBean contractorControllerBean;

    private ContractorDTO contractorDTO;

    public ResetContractorPageBean() {
    }

    public ContractorDTO getContractorDTO() {
        return contractorDTO;
    }

    public void setContractorDTO(ContractorDTO contractorDTO) {
        this.contractorDTO = contractorDTO;
    }

    @PostConstruct
    public void init() {
        contractorDTO = contractorControllerBean.getSelectedContractorDTO();
    }

    public String resetContractorAction() {
        try {
            contractorControllerBean.resetPersonalData(contractorDTO);
        } catch (AppBaseException ex) {
            Logger.getLogger(ResetContractorPageBean.class.getName()).log(Level.SEVERE, null, ex);
            ContextUtils.emitI18NMessage(null, ex.getMessage());
            return null;
        }
        return "listContractors";
    }

}
