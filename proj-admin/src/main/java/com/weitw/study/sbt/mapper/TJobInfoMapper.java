package com.weitw.study.sbt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weitw.study.sbt.domain.UmsMenu;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TJobInfoMapper extends BaseMapper<UmsMenu> {
    String findCronByUuid(String uuid);

    @MapKey("job_uuid")
    List<Map<String, String>> findCrons();
}
