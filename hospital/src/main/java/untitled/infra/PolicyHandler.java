package untitled.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import untitled.config.kafka.KafkaProcessor;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    HospitalRepository hospitalRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='HospitalizationReserved'"
    )
    public void wheneverHospitalizationReserved_CreateHospitalInfo(
        @Payload HospitalizationReserved hospitalizationReserved
    ) {
        HospitalizationReserved event = hospitalizationReserved;
        System.out.println(
            "\n\n##### listener CreateHospitalInfo : " +
            hospitalizationReserved +
            "\n\n"
        );

        // Sample Logic //
        Hospital.createHospitalInfo(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='HospitalizationCancelled'"
    )
    public void wheneverHospitalizationCanceled_UpdateStatus(
        @Payload HospitalizationCancelled hospitalizationCancelled
    ) {
        HospitalizationCancelled event = hospitalizationCancelled;
        System.out.println(
            "\n\n##### listener UpdateStatus : " +
            hospitalizationCancelled +
            "\n\n"
        );

        // Sample Logic //
        Hospital.updateStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
