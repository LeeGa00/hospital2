package untitled.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/beds")
@Transactional
public class BedsController {

    @Autowired
    BedsRepository bedsRepository;

    @RequestMapping(
        value = "/beds/initializebeds",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Beds initializeBeds(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /beds/initializeBeds  called #####");
        // Optional<Beds> optionalBeds = bedsRepository.findById(id);
        Beds beds = new Beds();

        // optionalBeds.orElseThrow(() -> new Exception("No Entity Found"));
        beds.initialize_beds();

        return beds;
    }
}
//>>> Clean Arch / Inbound Adaptor
