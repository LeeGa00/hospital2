package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class BedsHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Beds>> {

    @Override
    public EntityModel<Beds> process(EntityModel<Beds> model) {
        return model;
    }
}
