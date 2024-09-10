package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PatientModified extends AbstractEvent {

    private Long id;
    private String patientName;
    private String patientDisease;
    private String patientNumber;
    private String status;

    public PatientModified(Patient aggregate) {
        super(aggregate);
    }

    public PatientModified() {
        super();
    }
}
//>>> DDD / Domain Event
