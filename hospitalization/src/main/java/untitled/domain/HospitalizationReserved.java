package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class HospitalizationReserved extends AbstractEvent {

    private Long id;
    private Long patientId;
    private String status;
    private Long bedsId;

    public HospitalizationReserved(Hospitalization aggregate) {
        super(aggregate);
    }

    public HospitalizationReserved() {
        super();
    }
}
//>>> DDD / Domain Event
