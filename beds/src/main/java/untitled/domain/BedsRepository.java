package untitled.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "beds", path = "beds")
public interface BedsRepository extends PagingAndSortingRepository<Beds, Long> {
    @Query(
        value = "select beds " +
        "from Beds beds " +
        "where(:hpid is null or beds.hpid like %:hpid%)"
    )
    Beds findByGetBedsIdByhpid(String hpid);
}
