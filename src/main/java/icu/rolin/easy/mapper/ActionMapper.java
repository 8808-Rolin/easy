package icu.rolin.easy.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.caches.redis.RedisCache;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface ActionMapper {
}
