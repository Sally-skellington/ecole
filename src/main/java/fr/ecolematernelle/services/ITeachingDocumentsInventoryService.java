package fr.ecolematernelle.services;

import java.util.List;

import fr.ecolematernelle.entities.TeachingDocumentEntity;
import fr.ecolematernelle.exceptions.TeachingDocumentDuplicationException;

public interface ITeachingDocumentsInventoryService {
	
	List<TeachingDocumentEntity> getAllTeachingDocuments();
	
	TeachingDocumentEntity getTeachingDocumentsById();
	
	void addTeachingDocument(TeachingDocumentEntity document) throws TeachingDocumentDuplicationException;

}
