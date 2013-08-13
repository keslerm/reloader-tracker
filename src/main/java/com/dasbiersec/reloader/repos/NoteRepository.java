package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.domain.Batch;
import com.dasbiersec.reloader.domain.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Integer>
{
    public List<Batch> findNoteByRecipeId(Integer id);
}
