package untitled.domain;

import untitled.BedsApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Table(name="Beds_table")
@Data

//<<< DDD / Aggregate Root
public class Beds  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    private Long id;
    
    
    
    
    private String hospitalName;
    
    
    
    
    private Double lat;
    
    
    
    
    private Double lng;
    
    
    
    
    private Integer remain;
    
    
    
    
    private Integer totalBeds;
    
    
    
    
    private String hpid;

    @PostPersist
    public void onPostPersist(){
    
    }

    public static BedsRepository repository(){
        BedsRepository bedsRepository = BedsApplication.applicationContext.getBean(BedsRepository.class);
        return bedsRepository;
    }

    public void initialize beds(){
        //
    }



//<<< Clean Arch / Port Method
    public static void bedsUpdate(HospitalizationApproved hospitalizationApproved){
        
        //implement business logic here:

        /** Example 1:  new item 
        Beds beds = new Beds();
        repository().save(beds);

        */

        /** Example 2:  finding and process
        
        repository().findById(hospitalizationApproved.get???()).ifPresent(beds->{
            
            beds // do something
            repository().save(beds);


         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void bedsUpdate(Discharged discharged){
        
        //implement business logic here:

        /** Example 1:  new item 
        Beds beds = new Beds();
        repository().save(beds);

        */

        /** Example 2:  finding and process
        
        repository().findById(discharged.get???()).ifPresent(beds->{
            
            beds // do something
            repository().save(beds);


         });
        */

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
