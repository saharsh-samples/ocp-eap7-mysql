package org.saharsh.samples.resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.saharsh.samples.model.Sample;
import org.saharsh.samples.services.SamplesService;
import org.saharsh.samples.services.ServiceFactory;

@Path("samples")
public class Samples {

	private SamplesService service;

	@Inject
	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.service = serviceFactory.getSamplesService();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sample> getAll() {
		return service.listAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Sample add(@Valid Sample sample) {
		return service.collect(sample);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{uuid:[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}}")
	public Sample get(@PathParam("uuid") String uuid) {
		return service.get(uuid);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{uuid:[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}}")
	public Sample update(@PathParam("uuid") String uuid, String updatedValue) {
		return service.update(uuid, updatedValue);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{uuid:[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}}")
	public Sample delete(@PathParam("uuid") String uuid) {
		return service.delete(uuid);
	}

}
