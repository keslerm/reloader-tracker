package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.domain.Batch;
import com.dasbiersec.reloader.repos.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService
{
    @Autowired
    private BatchRepository batchRepository;

    public List<Batch> getBatches(Integer recipeId)
    {
        return batchRepository.findBatchByRecipeId(recipeId);
    }

    public Batch createBatch(Batch batch)
    {
        Batch saved = batchRepository.save(batch);
        return saved;
    }

    public Batch saveBatch(Batch batch)
    {
        return batchRepository.save(batch);
    }

    public Batch getBatch(Integer batchId)
    {
        return batchRepository.findOne(batchId);
    }
}
