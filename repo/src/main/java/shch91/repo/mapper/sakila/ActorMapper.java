package shch91.repo.mapper.sakila;

import com.ldy.shch91.daoentity.Actor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ActorMapper {
    Actor select(@Param("actorId") int actorId);

   void update(Actor actor);

   void insert(Actor actor);

    int insertOrUpdate(Actor actor);
}