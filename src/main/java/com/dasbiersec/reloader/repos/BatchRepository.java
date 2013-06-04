package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.model.Component;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends CrudRepository<Batch, Integer>
{
	Iterable<Batch> findByBullet(Component component);
	Iterable<Batch> findByBrass(Component component);
	Iterable<Batch> findByPrimer(Component component);
	Iterable<Batch> findByPowder(Component component);

    Iterable<Batch> findAllByUserId(Integer userId);
    Batch findByIdAndUserId(Integer id, Integer userId);

}
