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
import untitled.domain.HospitalizationCancelled;

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

    }

    public static HospitalRepository repository() {
        HospitalRepository hospitalRepository = HospitalApplication.applicationContext.getBean(
            HospitalRepository.class
        );
        return hospitalRepository;
    }

    public void approve() {
        HospitalizationApproved hospitalizationApproved = new HospitalizationApproved(this);
        hospitalizationApproved.publishAfterCommit();
    }

    public void reject() {
        HospitalizationRejected hospitalizationRejected = new HospitalizationRejected(this);
        hospitalizationRejected.publishAfterCommit();
    }

    public void discharge() {
        Discharged discharged = new Discharged(this);
        discharged.publishAfterCommit();
    }

    //<<< Clean Arch / Port Method
    public static void createHospitalInfo(
        HospitalizationReserved hospitalizationReserved
    ) {
        Hospital hospital = new Hospital();
        hospital.setBedsId(hospitalizationReserved.getBedsId());
        hospital.setPatientId(hospitalizationReserved.getPatientId());
        hospital.setHospitalizationId(hospitalizationReserved.getId());
        hospital.setStatus("승인대기중");
        repository().save(hospital);
    }
    //>>> Clean Arch / Port Method

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatus(
        HospitalizationCancelled hospitalizationCancelled
    ) {
        repository().findById(Long.valueOf(hospitalizationCancelled.getBedsId())).ifPresent(hospital->{
            
            if (!hospital.getStatus().equals("승인")){
                hospital.setStatus("예약취소됨"); // do something
                repository().save(hospital);
            } else {
                // 이벤트를 발행해야하는지 궁금
                System.out.println(
                    "\n\n##### 예약취소 불가능함 : " +
                    "hospital.java - updateStatus 에서 예외처리" +
                    "\n\n"
                );
            }

         });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
