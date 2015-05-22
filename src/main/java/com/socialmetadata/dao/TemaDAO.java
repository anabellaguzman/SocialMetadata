package com.socialmetadata.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.socialmetadata.model.Tema;

public class TemaDAO {
	
	@Autowired
	private SessionFactory session;

	public void add(Tema tema) {
		session.getCurrentSession().save(tema);
		
	}

	public void edit(Tema tema) {
		session.getCurrentSession().update(tema);
		
	}

	public void delete(int idTema) {
		session.getCurrentSession().delete(getTema(idTema));
		
	}

	public Tema getTema(int idTema) {
				
		return (Tema)session.getCurrentSession().get(Tema.class, idTema);
		
		
	}

	public List getAllTemas() {
			
		
		return session.getCurrentSession().createQuery("from Tema").list();
	}
	
}