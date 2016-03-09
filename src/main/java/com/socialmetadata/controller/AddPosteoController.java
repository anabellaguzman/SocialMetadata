package com.socialmetadata.controller;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.socialmetadata.model.Archivo;
import com.socialmetadata.model.Comentario;
import com.socialmetadata.model.Error;
import com.socialmetadata.model.Item;
import com.socialmetadata.model.Posteo;
import com.socialmetadata.model.Usuario;
import com.socialmetadata.service.ItemService;
import com.socialmetadata.service.PosteoService;
import com.socialmetadata.service.UsuarioService;
import com.socialmetadata.utilities.FileFormBean;

@Controller
public class AddPosteoController {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private PosteoService posteoService;
	
	
	
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public void addComment(@RequestParam String tituloC, @RequestParam String comentarioC, @RequestParam int idItem){
    
	      Posteo comentario = createComentario();
	      addPosteo(comentario, tituloC, comentarioC, idItem);
	      
	}
	
	@RequestMapping(value = "/addError", method = RequestMethod.POST)
	public void addError(@RequestParam String tituloE, @RequestParam String comentarioE, @RequestParam int idItem){
			Posteo error = createError();
			addPosteo(error, tituloE, comentarioE, idItem);
		      
	}
	
	@RequestMapping(value = "/addArchivo", method = RequestMethod.POST)
	 	public void addArchivo( @RequestParam int idItem, @RequestParam String titulo, @ModelAttribute FileFormBean fileFormBean, HttpServletRequest request){
	 		
	 			System.out.println(idItem);
	 			System.out.println(titulo);
	 			
	 			Posteo archivo = createArchivo();
	 			
	 			CommonsMultipartFile uploaded = fileFormBean.getFichero();
	 			uploaded.getOriginalFilename();
	 			
	 			int idPosteo = addPosteo(archivo, titulo, uploaded.getOriginalFilename(), idItem);
	 			
	 			
	 			FileUploadController fileUploadController = new FileUploadController();
	 			fileUploadController.guardaFichero(fileFormBean, request, idPosteo);
	 			
	 			System.out.println("id posteo: "+ idPosteo);
	 		      
	 	}
	
	
	
	private int addPosteo(Posteo posteo, String titulo, String comentario, int idItem){
		
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String username = auth.getName(); 
   
	      Usuario usuario = usuarioService.getByUsername(username);
	      Item item = itemService.getItem(idItem);

		  Date date = new Date();
		  
		  posteo.setUsuario(usuario);
	      posteo.setItem(item);
	      posteo.setTitulo(titulo);
	      posteo.setComentario(comentario);
	      posteo.setFecha(date);
	      
	      return posteoService.add(posteo);
	}
	
	private Posteo createComentario(){
		 Posteo comentario = new Comentario();
		 return comentario;
	}
	
	private Posteo createError(){
		Error error = new Error();
		return error;
	}
	
	private Posteo createArchivo(){
		Archivo archivo = new Archivo();
		return archivo;
	}
	
	
	

}
