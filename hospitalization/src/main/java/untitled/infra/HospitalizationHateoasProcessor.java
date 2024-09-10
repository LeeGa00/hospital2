package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class HospitalizationHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Hospitalization>> {

    @Override
    public EntityModel<Hospitalization> process(
        EntityModel<Hospitalization> model
    ) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "//hospitalizationcancel"
                )
                .withRel("/hospitalizationcancel")
        );

        return model;
    }
}
