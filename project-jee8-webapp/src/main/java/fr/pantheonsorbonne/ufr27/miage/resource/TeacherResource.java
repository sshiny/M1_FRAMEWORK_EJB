package fr.pantheonsorbonne.ufr27.miage.resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.pantheonsorbonne.ufr27.miage.ClassRoomService;
import fr.pantheonsorbonne.ufr27.miage.ClassRoomServiceImpl;
import fr.pantheonsorbonne.ufr27.miage.model.Class;
import fr.pantheonsorbonne.ufr27.miage.model.ObjectFactory;
import fr.pantheonsorbonne.ufr27.miage.model.Teacher;

@Stateless
@Path("teacher")
public class TeacherResource {

	@EJB
	ClassRoomService session;

	private static final ObjectFactory factory = new ObjectFactory();

	@Path("/{teacherId}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Teacher readTeacher(@PathParam("teacherId") final long teacherId) {
		return factory.createTeacher();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createTeacher(Teacher teacher) {
		session.businessMethod();
		return Response.accepted().build();
	}

	@Path("/{teacherId}/class/{classId}")
	@PUT
	public Response updateTeacher(@PathParam("teacherId") long teacherId, @PathParam("classId") long classId) {
		return Response.accepted().build();
	}

	@Path("/{teacherId}/class/")
	@POST
	public Response updateTeacher(@PathParam("teacherId") long teacherId, Class klass) {
		return Response.accepted().build();
	}
}
