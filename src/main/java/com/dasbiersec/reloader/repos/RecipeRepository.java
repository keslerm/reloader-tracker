package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.domain.Recipe;
import com.dasbiersec.reloader.domain.Component;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer>
{
	Iterable<Recipe> findByBullet(Component component);
	Iterable<Recipe> findByBrass(Component component);
	Iterable<Recipe> findByPrimer(Component component);
	Iterable<Recipe> findByPowder(Component component);

    Iterable<Recipe> findAllByUserId(Integer userId);
    Recipe findByIdAndUserId(Integer id, Integer userId);

}
