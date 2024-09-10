package untitled.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "hospitals", path = "hospitals")
public interface HospitalRepository
    extends PagingAndSortingRepository<Hospital, Long> {
    @Query(
        value = "select hospital " +
        "from Hospital hospital " +
        "where(:bedsId is null or hospital.bedsId = :bedsId)"
    )
    List<Hospital> findByReservationInfo(Long bedsId, Pageable pageable);
}
