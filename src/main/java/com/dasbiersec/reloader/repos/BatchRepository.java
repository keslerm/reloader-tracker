package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.domain.Batch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends CrudRepository<Batch, Integer>
{
    public List<Batch> findBatchByRecipeId(Integer id);
}
