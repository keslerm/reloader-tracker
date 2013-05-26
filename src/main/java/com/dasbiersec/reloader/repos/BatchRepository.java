package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.model.Batch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends CrudRepository<Batch, Integer>
{
}
