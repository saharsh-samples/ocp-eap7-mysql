package org.saharsh.samples;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.saharsh.samples.resources.Samples;

@ApplicationPath("api")
public class SamplesApi extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> resources = new HashSet<>();
		resources.add(Samples.class);
		return resources;
	}

}
