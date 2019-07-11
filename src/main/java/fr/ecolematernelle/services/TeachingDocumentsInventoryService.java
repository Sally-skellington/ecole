package fr.ecolematernelle.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import fr.ecolematernelle.entities.TeachingDocumentEntity;
import fr.ecolematernelle.exceptions.TeachingDocumentDuplicationException;
import fr.ecolematernelle.repositories.TeachingDocumentRepository;

@Service
public class TeachingDocumentsInventoryService implements ITeachingDocumentsInventoryService{
	
	@Autowired
	private TeachingDocumentRepository teachingDocRepo;

	@Override
	public List<TeachingDocumentEntity> getAllTeachingDocuments() throws HttpStatusCodeException{
		List<TeachingDocumentEntity> teachingDocs = new ArrayList<>();
		Iterable<TeachingDocumentEntity> it = teachingDocRepo.findAll();
		it.forEach(teachingDocs :: add);
		return teachingDocs;		
	}

	@Override
	public void addTeachingDocument(TeachingDocumentEntity document) throws TeachingDocumentDuplicationException{
		if (!checkIfExist(document.getTitre())) {
			teachingDocRepo.save(document);	
		} else {
			throw new TeachingDocumentDuplicationException("Ce document existe déjà");
		}
			
	}
	
	private boolean checkIfExist(String title) {
		List<TeachingDocumentEntity> documents = getAllTeachingDocuments();
		List<String> titles = documents.stream().map(doc -> doc.getTitre()).collect(Collectors.toList());
		return titles.stream().anyMatch(name -> name.equals(title));	
	}

	@Override
	public TeachingDocumentEntity getTeachingDocumentsById() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
