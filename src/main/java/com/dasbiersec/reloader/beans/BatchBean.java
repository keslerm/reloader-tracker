package com.dasbiersec.reloader.beans;

import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.service.ReloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Component
@Scope("session")
public class BatchBean implements Serializable
{
    @Autowired
    private ReloaderService reloaderService;

    private Iterable<Batch> batches;

    public Iterable<Batch> getBatches()
    {
        if (batches == null)
            batches = reloaderService.getAllBatches();

        return batches;
    }

    public void setBatches(Iterable<Batch> batches)
    {
        this.batches = batches;
    }
}
