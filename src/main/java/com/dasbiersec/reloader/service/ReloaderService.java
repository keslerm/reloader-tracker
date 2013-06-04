package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.UserDetailsObject;
import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.helpers.BatchHelper;
import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.model.Component;
import com.dasbiersec.reloader.repos.BatchRepository;
import com.dasbiersec.reloader.repos.ComponentRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReloaderService
{
	private Logger log = Logger.getLogger(getClass());

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private ComponentRepository componentRepository;

	@Autowired
	private BatchHelper batchHelper;

	public Batch getBatchById(Integer id)
	{

		Batch batch = batchRepository.findByIdAndUserId(id, getCurrentUser());

		batchHelper.setCostPerRound(batch);

		return batch;
	}

	public Iterable<Batch> getAllBatches()
	{
		Iterable<Batch> batches = batchRepository.findAllByUserId(getCurrentUser());

		for (Batch batch : batches)
		{
			batchHelper.setCostPerRound(batch);
		}

		return batches;
	}

	public Batch saveBatch(Batch batch)
	{
		Batch rb = batchRepository.save(batch);
		return getBatchById(rb.getId());
	}

	public void deleteBatchById(Integer id)
	{
		batchRepository.delete(id);
	}

	public Iterable<Component> getAllComponents()
	{
		Iterable<Component> components = componentRepository.findAll();

		for (Component component : components)
		{
			setRemainingComponentAmount(component);
		}

		return components;
	}

	public Component getComponentById(Integer id)
	{
		Component component = componentRepository.findOne(id);
		setRemainingComponentAmount(component);
		return component;
	}

	public Component saveComponent(Component component)
	{
		Component c1 = componentRepository.save(component);
		return componentRepository.findOne(c1.getId());
	}

	public void deleteComponentById(Integer id)
	{
		componentRepository.delete(id);
	}

	public Iterable<Component> findComponentByType(ComponentType type)
	{
		Iterable<Component> components = componentRepository.findComponentByType(type);

		for (Component component : components)
		{
			setRemainingComponentAmount(component);
		}

		return components;
	}

	private void setRemainingComponentAmount(Component component)
	{

		Iterable<Batch> batches = null;

		switch (component.getType())
		{
			case Brass:
				batches = batchRepository.findByBrass(component);
				break;

			case Primer:
				batches = batchRepository.findByPrimer(component);
				break;

			case Bullet:
				batches = batchRepository.findByBullet(component);
				break;

			case Powder:
				batches = batchRepository.findByPowder(component);
				break;
		}

		if (batches != null)
		{
			BigDecimal remaining = component.getAmount();

			for (Batch batch : batches)
			{
				if (component.getType() == ComponentType.Powder)
					remaining = remaining.subtract(new BigDecimal(batch.getCount()).multiply(batch.getPowderCharge()));
				else
					remaining = remaining.subtract(new BigDecimal(batch.getCount()));
			}

			component.setRemaining(remaining);
		}
	}

    private Integer getCurrentUser()
    {
        UserDetailsObject userDetailsObject = (UserDetailsObject) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetailsObject.getId();
    }
}
