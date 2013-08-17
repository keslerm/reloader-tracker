package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.domain.Batch;
import com.dasbiersec.reloader.repos.BatchRepository;
import com.dasbiersec.reloader.repos.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BatchService
{
    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Batch> getBatches(Integer recipeId)
    {
        return batchRepository.findBatchByRecipeId(recipeId);
    }

    public Batch createBatch(Integer recipeId, Batch batch)
    {
        batch.setRecipe(recipeRepository.findOne(recipeId));
        Batch saved = batchRepository.save(batch);
        return saved;
    }

    public Batch saveBatch(Integer batchId, Batch batch)
    {
        Batch existingBatch = batchRepository.findOne(batchId);

        if (existingBatch == null)
            throw new EntityNotFoundException("Could not find existing batch");

	    batch.setId(batchId);
	    batch.setRecipe(existingBatch.getRecipe());

        batchRepository.save(batch);

        return batchRepository.findOne(batchId);
    }

    public Batch getBatch(Integer batchId)
    {
        return batchRepository.findOne(batchId);
    }
}
