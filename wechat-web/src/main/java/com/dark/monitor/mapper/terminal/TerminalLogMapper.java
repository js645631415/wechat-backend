package com.dark.monitor.mapper.terminal;

import com.dark.monitor.trans.ListUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

@Mapper
public interface TerminalLogMapper {
    @SelectProvider(type = Provider.class)
    List<Long> findTerminalLogByParams(Map params);

    class Provider implements ProviderMethodResolver {
        public String findTerminalLogByParams(Map params){
            SQL sql = new SQL();
            sql.SELECT("ter_log.id");
            sql.FROM("ter_log");
            sql.INNER_JOIN("ter_dev on ter_dev.id=ter_log.dev_id");
            sql.INNER_JOIN(" ter_biz_type ON ter_biz_type.id=ter_log.biz_type_id");
            if (params.get("orgIds")!=null){
                sql.WHERE("ter_dev.organiztion_id IN ("+ ListUtils.list2In(params.get("orgIds").toString()) +")");
            }
            return sql.toString();
        }
    }

}
