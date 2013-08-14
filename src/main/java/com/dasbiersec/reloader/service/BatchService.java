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
        // does batch exist
        if (batch.getId() != null)
            throw new EntityExistsException("Batch id exists in database");

        batch.setRecipe(recipeRepository.findOne(recipeId));
        Batch saved = batchRepository.save(batch);
        return saved;
    }

    public Batch saveBatch(Integer batchId, Batch batch)
    {
        Batch existingBatch = batchRepository.findOne(batchId);

        if (existingBatch == null)
            throw new EntityNotFoundException("Could not find existing batch");

        // Don't overwrite these fields
        batch.setId(batchId);

        return batchRepository.save(batch);
    }

    public Batch getBatch(Integer batchId)
    {
        return batchRepository.findOne(batchId);
    }
}
