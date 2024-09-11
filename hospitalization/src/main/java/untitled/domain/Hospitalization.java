package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.HospitalizationApplication;
import untitled.domain.HospitalizationReserved;

@Entity
@Table(name = "Hospitalization_table")
@Data
//<<< DDD / Aggregate Root
public class Hospitalization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long patientId;

    private Long bedsId;

    private String status;

    @PrePersist
    public void onPrePersist() {
        this.setStatus("병원승인 대기");
        HospitalizationReserved hospitalizationReserved = new HospitalizationReserved(this);
        hospitalizationReserved.publishAfterCommit();
    }

    public static HospitalizationRepository repository() {
        HospitalizationRepository hospitalizationRepository = HospitalizationApplication.applicationContext.getBean(
            HospitalizationRepository.class
        );
        return hospitalizationRepository;
    }

    //<<< Clean Arch / Port Method
    public void hospitalizationCancel() {
        HospitalizationCancelled hospitalizationCanceled = new HospitalizationCancelled(this);
        hospitalizationCanceled.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void updateStatus(
        HospitalizationApproved hospitalizationApproved
    ) {
        
        repository().findById(hospitalizationApproved.getHospitalizationId()).ifPresent(hospitalization->{
            hospitalization.setStatus("이송시작"); // do something
            repository().save(hospitalization);
         });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatus(
        HospitalizationRejected hospitalizationRejected
    ) {
        repository().findById(hospitalizationRejected.getHospitalizationId()).ifPresent(hospitalization->{
            hospitalization.setStatus("요청거절"); // do something
            repository().save(hospitalization);
         });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
