package com.dasbiersec.reloader.mapper;

import com.dasbiersec.reloader.domain.Batch;
import com.dasbiersec.reloader.dto.batch.BatchDTO;

import java.util.ArrayList;
import java.util.List;

public class BatchMapper
{

    public static BatchDTO domainToDTO(Batch batch)
    {
        BatchDTO dto = new BatchDTO();

        dto.id = batch.getId();
        dto.count = batch.getCount();
        dto.note = batch.getNote();

        return dto;
    }

    public static void copyDTOtoDomain(BatchDTO dto, Batch batch)
    {
        batch.setNote(dto.note);
        batch.setCount(dto.count);
    }

    public static List<BatchDTO> domainToDTO (List<Batch> batches)
    {
        List<BatchDTO> dtos = new ArrayList<BatchDTO>();

        for (Batch batch : batches)
        {
            dtos.add(domainToDTO(batch));
        }

        return dtos;
    }

    public static Batch dtoToDomain(BatchDTO dto)
    {
        Batch batch = new Batch();

        copyDTOtoDomain(dto, batch);

        return batch;
    }
}
