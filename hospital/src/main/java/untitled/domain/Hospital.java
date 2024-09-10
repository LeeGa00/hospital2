package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.HospitalApplication;
import untitled.domain.Discharged;
import untitled.domain.HospitalizationApproved;
import untitled.domain.HospitalizationRejected;

@Entity
@Table(name = "Hospital_table")
@Data
//<<< DDD / Aggregate Root
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long bedsId;

    private Date startDate;

    private Date endDate;

    private Long patientId;

    private String status;

    private Long hospitalizationId;

    @PostPersist
    public void onPostPersist() {
        HospitalizationApproved hospitalizationApproved = new HospitalizationApproved(
            this
        );
        hospitalizationApproved.publishAfterCommit();

        HospitalizationRejected hospitalizationRejected = new HospitalizationRejected(
            this
        );
        hospitalizationRejected.publishAfterCommit();

        Discharged discharged = new Discharged(this);
        discharged.publishAfterCommit();
    }

    public static HospitalRepository repository() {
        HospitalRepository hospitalRepository = HospitalApplication.applicationContext.getBean(
            HospitalRepository.class
        );
        return hospitalRepository;
    }

    //<<< Clean Arch / Port Method
    public void approve() {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void reject() {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void createHospitalInfo(
        HospitalizationReserved hospitalizationReserved
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Hospital hospital = new Hospital();
        repository().save(hospital);

        */

        /** Example 2:  finding and process
        
        repository().findById(hospitalizationReserved.get???()).ifPresent(hospital->{
            
            hospital // do something
            repository().save(hospital);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
