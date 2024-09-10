package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PatientRegistered extends AbstractEvent {

    private Long id;
    private String patientName;
    private String patientDisease;
    private String patientNumber;
    private String status;

    public PatientRegistered(Patient aggregate) {
        super(aggregate);
    }

    public PatientRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
