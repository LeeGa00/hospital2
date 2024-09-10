package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PatientDeleted extends AbstractEvent {

    private Long id;
    private String patientName;
    private String patientDisease;
    private String patientNumber;
    private String status;

    public PatientDeleted(Patient aggregate) {
        super(aggregate);
    }

    public PatientDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
