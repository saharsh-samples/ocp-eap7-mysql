package org.saharsh.samples.services.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import javax.ws.rs.NotFoundException;

import org.saharsh.samples.model.Sample;
import org.saharsh.samples.services.SamplesService;

@ApplicationScoped
@Named("persistent")
public class DBSamplesService implements SamplesService {

	private EntityManager entityManager;
	private UserTransaction userTransaction;

	@PersistenceContext(unitName = "samplesdb")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Resource
	public void setUserTransaction(UserTransaction userTransaction) {
		this.userTransaction = userTransaction;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Sample> listAll() {
		final Query query = entityManager.createQuery("SELECT s FROM sample s", Sample.class);
		return query.getResultList();
	}

	@Override
	public Sample collect(Sample sample) {

		sample.setId(UUID.randomUUID().toString());

		try {
			userTransaction.begin();
			entityManager.persist(sample);
			userTransaction.commit();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}

		return get(sample.getId());
	}

	@Override
	public Sample get(String uuid) {
		final Sample sample = entityManager.find(Sample.class, uuid);
		if (sample == null) {
			throw new NotFoundException(String.format("No sample found for %s", uuid));
		}
		return sample;
	}

	@Override
	public Sample update(String uuid, String updatedValue) {

		try {

			userTransaction.begin();

			final Sample sample = get(uuid);
			sample.setValue(updatedValue);

			userTransaction.commit();

			return sample;

		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Sample delete(String uuid) {

		try {

			userTransaction.begin();

			final Sample sample = get(uuid);
			entityManager.remove(sample);

			userTransaction.commit();

			return sample;

		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}
