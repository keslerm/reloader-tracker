package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.model.Round;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends CrudRepository<Round, Integer>
{
}
