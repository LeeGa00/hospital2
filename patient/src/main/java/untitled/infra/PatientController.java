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
// @RequestMapping(value="/patients")
@Transactional
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @RequestMapping(
        value = "/patients/{id}/patientupdate",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Patient patientUpdate(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /patient/patientUpdate  called #####");
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        optionalPatient.orElseThrow(() -> new Exception("No Entity Found"));
        Patient patient = optionalPatient.get();
        patient.patientUpdate();

        patientRepository.save(patient);
        return patient;
    }

    @RequestMapping(
        value = "/patients/{id}//patientdelete",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Patient patientDelete(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /patient/patientDelete  called #####");
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        optionalPatient.orElseThrow(() -> new Exception("No Entity Found"));
        Patient patient = optionalPatient.get();
        patient.patientDelete();

        patientRepository.save(patient);
        return patient;
    }
}
//>>> Clean Arch / Inbound Adaptor
