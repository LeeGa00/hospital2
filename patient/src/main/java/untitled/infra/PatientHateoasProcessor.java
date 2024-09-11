package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class PatientHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Patient>> {

    @Override
    public EntityModel<Patient> process(EntityModel<Patient> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/patientupdate")
                .withRel("patientupdate")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/patientdelete")
                .withRel("patientdelete")
        );

        return model;
    }
}
