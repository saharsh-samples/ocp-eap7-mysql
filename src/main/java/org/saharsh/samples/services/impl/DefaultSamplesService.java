package org.saharsh.samples.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.NotFoundException;

import org.saharsh.samples.model.Sample;
import org.saharsh.samples.services.SamplesService;

@ApplicationScoped
@Named("inmem")
public class DefaultSamplesService implements SamplesService {

	Map<String, Sample> samples = new ConcurrentHashMap<>();

	@Override
	public List<Sample> listAll() {
		final ArrayList<Sample> all = new ArrayList<>();
		for (final Sample sample : samples.values()) {
			all.add(sample);
		}
		return all;
	}

	@Override
	public Sample collect(Sample sample) {
		sample.setId(UUID.randomUUID().toString());
		samples.put(sample.getId(), sample);
		return sample;
	}

	@Override
	public Sample get(String uuid) {
		final Sample sample = samples.get(uuid);
		if (sample == null) {
			throw new NotFoundException(String.format("No sample found for %s", uuid));
		}
		return sample;
	}

	@Override
	public Sample update(String uuid, String updatedValue) {
		final Sample sample = get(uuid);
		sample.setValue(updatedValue);
		return sample;
	}

	@Override
	public Sample delete(String uuid) {
		final Sample sample = samples.remove(uuid);
		if (sample == null) {
			throw new NotFoundException(String.format("No sample found for %s", uuid));
		}
		return sample;
	}

}
