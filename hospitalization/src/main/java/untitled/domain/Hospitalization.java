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

    @PostPersist
    public void onPostPersist() {
        HospitalizationReserved hospitalizationReserved = new HospitalizationReserved(
            this
        );
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
        //implement business logic here:

        HospitalizationCanceled hospitalizationCanceled = new HospitalizationCanceled(
            this
        );
        hospitalizationCanceled.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void updateStatus(
        HospitalizationApproved hospitalizationApproved
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Hospitalization hospitalization = new Hospitalization();
        repository().save(hospitalization);

        */

        /** Example 2:  finding and process
        
        repository().findById(hospitalizationApproved.get???()).ifPresent(hospitalization->{
            
            hospitalization // do something
            repository().save(hospitalization);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatus(
        HospitalizationRejected hospitalizationRejected
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Hospitalization hospitalization = new Hospitalization();
        repository().save(hospitalization);

        */

        /** Example 2:  finding and process
        
        repository().findById(hospitalizationRejected.get???()).ifPresent(hospitalization->{
            
            hospitalization // do something
            repository().save(hospitalization);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
