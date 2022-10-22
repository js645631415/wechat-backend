package com.dark.monitor.mapper.terminal;

import com.dark.monitor.trans.ListUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

@Mapper
public interface TerminalDevMapper {
    @SelectProvider(type =Provider.class)
     List<Long> findTerminalByParams(Map params);
    class Provider implements ProviderMethodResolver {
        public String findTerminalByParams(Map params){
            SQL sql = new SQL();
            sql.SELECT("`ter_dev`.id")
                    .FROM("`ter_dev`");
            if (params.get("orgIds")!=null)
                sql.WHERE("`ter_dev`.organiztion_id in ( "+ ListUtils.list2In(params.get("orgIds").toString()) +" )");
            return sql.toString();
        }
    }
}
