package fr.ecolematernelle.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import fr.ecolematernelle.entities.TeachingDocumentEntity;
import fr.ecolematernelle.exceptions.TeachingDocumentDuplicationException;
import fr.ecolematernelle.models.TeachingDocument;
import fr.ecolematernelle.services.ITeachingDocumentsInventoryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TeachingDocumentsInventoryController {

	@Autowired
	private ITeachingDocumentsInventoryService service;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@RequestMapping(value="/inventaire/documents", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TeachingDocument> getAllDocuments() {
		List<TeachingDocument> documents = new ArrayList<TeachingDocument>();
		try {
			documents = service.getAllTeachingDocuments().stream().map(doc -> modelMapper.map(doc, TeachingDocument.class)).collect(Collectors.toList());	
		}catch (HttpStatusCodeException e){
			e.printStackTrace();
		}	
		return documents;		
	}

	@RequestMapping(value = "/inventaire/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addDocument(@RequestBody TeachingDocumentEntity document) {
		ModelMapper modelMapper = new ModelMapper();
		try {
			service.addTeachingDocument(modelMapper.map(document, TeachingDocumentEntity.class));
		} catch (TeachingDocumentDuplicationException e) {
			e.printStackTrace();
		}
	}

}
