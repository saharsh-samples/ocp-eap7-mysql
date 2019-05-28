package org.saharsh.samples.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceFactory {

	private SamplesService samplesService;

	@Inject
	public void setSamplesService(@Named("inmem") SamplesService samplesService) {
		this.samplesService = samplesService;
	}

	public SamplesService getSamplesService() {
		return samplesService;
	}

}
