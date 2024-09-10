package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class HospitalizationRejected extends AbstractEvent {

    private Long id;
    private Long patientId;
    private Long hospitalizationId;
}
