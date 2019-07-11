package fr.ecolematernelle.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.ecolematernelle.entities.TeachingDocumentEntity;

@Repository
public interface TeachingDocumentRepository extends CrudRepository<TeachingDocumentEntity, Long>{

}
