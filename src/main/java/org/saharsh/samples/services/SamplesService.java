package org.saharsh.samples.services;

import java.util.List;

import org.saharsh.samples.model.Sample;

public interface SamplesService {

	List<Sample> listAll();

	Sample collect(Sample sample);

	Sample get(String uuid);

	Sample update(String uuid, String updatedValue);

	Sample delete(String uuid);

}
