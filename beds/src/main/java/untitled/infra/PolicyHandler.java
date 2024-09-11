package untitled.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
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
    BedsRepository bedsRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='HospitalizationApproved'"
    )
    public void wheneverHospitalizationApproved_BedsUpdate(
        @Payload HospitalizationApproved hospitalizationApproved
    ) {
        HospitalizationApproved event = hospitalizationApproved;
        System.out.println(
            "\n\n##### listener BedsUpdate : " +
            hospitalizationApproved +
            "\n\n"
        );

        Beds.bedsUpdate(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Discharged'"
    )
    public void wheneverDischarged_BedsUpdate(@Payload Discharged discharged) {
        Discharged event = discharged;
        System.out.println(
            "\n\n##### listener BedsUpdate : " + discharged + "\n\n"
        );

        Beds.bedsUpdate(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
