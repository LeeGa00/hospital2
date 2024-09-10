package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Discharged extends AbstractEvent {

    private Long id;
    private Long bedsId;
    private Long patientId;

    public Discharged(Hospital aggregate) {
        super(aggregate);
    }

    public Discharged() {
        super();
    }
}
//>>> DDD / Domain Event
