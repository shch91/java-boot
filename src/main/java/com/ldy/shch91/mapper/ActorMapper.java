package com.ldy.shch91.mapper;

import com.ldy.shch91.daoentity.Actor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ActorMapper {
    Actor select(@Param("actorId") int actorId);

   void update(Actor actor);
}