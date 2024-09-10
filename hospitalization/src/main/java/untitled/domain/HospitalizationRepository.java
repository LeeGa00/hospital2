package untitled.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "hospitalizations",
    path = "hospitalizations"
)
public interface HospitalizationRepository
    extends PagingAndSortingRepository<Hospitalization, Long> {}
