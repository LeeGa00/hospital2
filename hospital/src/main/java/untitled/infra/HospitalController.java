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
// @RequestMapping(value="/hospitals")
@Transactional
public class HospitalController {

    @Autowired
    HospitalRepository hospitalRepository;

    @RequestMapping(
        value = "/hospitals/{id}/approve",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Hospital approve(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /hospital/approve  called #####");
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);

        optionalHospital.orElseThrow(() -> new Exception("No Entity Found"));
        Hospital hospital = optionalHospital.get();
        hospital.approve();

        hospital.setStatus("승인");
        hospitalRepository.save(hospital);
        return hospital;
    }

    @RequestMapping(
        value = "/hospitals/{id}/reject",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Hospital reject(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /hospital/reject  called #####");
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);

        optionalHospital.orElseThrow(() -> new Exception("No Entity Found"));
        Hospital hospital = optionalHospital.get();
        hospital.reject();

        hospital.setStatus("거절");
        hospitalRepository.save(hospital);
        return hospital;
    }

    @RequestMapping(
        value = "/hospitals/{id}/discharge",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Hospital discharge(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /hospital/discharge  called #####");
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);

        optionalHospital.orElseThrow(() -> new Exception("No Entity Found"));
        Hospital hospital = optionalHospital.get();
        hospital.discharge();

        hospital.setStatus("퇴원");
        hospitalRepository.save(hospital);
        return hospital;
    }
}
//>>> Clean Arch / Inbound Adaptor
