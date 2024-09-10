package untitled.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/hospitalizations")
@Transactional
public class HospitalizationController {

    @Autowired
    HospitalizationRepository hospitalizationRepository;

    @RequestMapping(
        value = "/hospitalizations/{id}//hospitalizationcancel",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Hospitalization hospitalizationCancel(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /hospitalization/hospitalizationCancel  called #####"
        );
        Optional<Hospitalization> optionalHospitalization = hospitalizationRepository.findById(
            id
        );

        optionalHospitalization.orElseThrow(() ->
            new Exception("No Entity Found")
        );
        Hospitalization hospitalization = optionalHospitalization.get();
        hospitalization.hospitalizationCancel();

        hospitalizationRepository.save(hospitalization);
        return hospitalization;
    }
}
//>>> Clean Arch / Inbound Adaptor
