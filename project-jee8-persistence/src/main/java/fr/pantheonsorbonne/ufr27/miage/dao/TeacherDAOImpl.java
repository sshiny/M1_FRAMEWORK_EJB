package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pantheonsorbonne.ufr27.miage.model.ObjectFactory;

public class TeacherDAOImpl implements TeacherDAO {

	@Inject
	EntityManager em;
	
	

	public TeacherDAOImpl() {

	}

	private static final ObjectFactory factory = new ObjectFactory();

	public void createTeacher(String name, double salary) {
	
		fr.pantheonsorbonne.ufr27.miage.dao.Teacher t = new fr.pantheonsorbonne.ufr27.miage.dao.Teacher();
		t.setName(name);
		t.setSalary(salary);
		em.persist(t);

	}

}
