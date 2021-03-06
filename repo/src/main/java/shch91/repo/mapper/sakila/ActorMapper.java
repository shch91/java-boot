package shch91.repo.mapper.sakila;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shch91.repo.daoentity.Actor;

@Mapper
public interface ActorMapper {
    Actor select(@Param("actorId") int actorId);

   int update(Actor actor);

   int insert(Actor actor);

    int insertOrUpdate(Actor actor);
}