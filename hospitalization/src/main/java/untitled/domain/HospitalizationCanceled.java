package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class HospitalizationCanceled extends AbstractEvent {

    private Long id;
    private Long patientId;
    private String status;
    private Long bedsId;

    public HospitalizationCanceled(Hospitalization aggregate) {
        super(aggregate);
    }

    public HospitalizationCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
