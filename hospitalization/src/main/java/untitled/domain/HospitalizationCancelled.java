package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class HospitalizationCancelled extends AbstractEvent {

    private Long id;
    private Long patientId;
    private String status;
    private Long bedsId;

    public HospitalizationCancelled(Hospitalization aggregate) {
        super(aggregate);
    }

    public HospitalizationCancelled() {
        super();
    }
}
//>>> DDD / Domain Event
