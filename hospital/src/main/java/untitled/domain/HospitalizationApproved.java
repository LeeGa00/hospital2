package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class HospitalizationApproved extends AbstractEvent {

    private Long id;
    private Long bedsId;
    private Long patientId;
    private Long hospitalizationId;

    public HospitalizationApproved(Hospital aggregate) {
        super(aggregate);
    }

    public HospitalizationApproved() {
        super();
    }
}
//>>> DDD / Domain Event
