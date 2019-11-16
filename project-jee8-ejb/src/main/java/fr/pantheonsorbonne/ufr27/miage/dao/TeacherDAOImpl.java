package fr.pantheonsorbonne.ufr27.miage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



public class TeacherDAOImpl implements TeacherDAO {

	@Inject
	EntityManager em;

	public TeacherDAOImpl() {

	}

	

	public void createTeacher(String name, double salary) {

		fr.pantheonsorbonne.ufr27.miage.dao.Teacher t = new fr.pantheonsorbonne.ufr27.miage.dao.Teacher();
		t.setName(name);
		t.setSalary(salary);
		em.persist(t);

	}

}
