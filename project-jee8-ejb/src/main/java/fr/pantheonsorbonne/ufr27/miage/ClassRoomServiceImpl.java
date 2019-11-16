/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pantheonsorbonne.ufr27.miage;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pantheonsorbonne.ufr27.miage.dao.Teacher;
import fr.pantheonsorbonne.ufr27.miage.dao.TeacherDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.TeacherDAOImpl;

import javax.ejb.LocalBean;

@Stateless
public class ClassRoomServiceImpl implements ClassRoomService {

	@Inject
	TeacherDAO teacherDAO;

	@Override
	public String businessMethod() {

		System.out.println(teacherDAO);

		teacherDAO.createTeacher("nicolas", 123);

		return "Got It from EJB";
	}

}
