package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class HospitalizationReserved extends AbstractEvent {

    private Long id;
    private Long patientId;
    private String status;
    private Long bedsId;
}
