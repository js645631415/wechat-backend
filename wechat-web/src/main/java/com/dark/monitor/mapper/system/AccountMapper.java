package com.dark.monitor.mapper.system;

import com.dark.monitor.trans.ListUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;

import java.util.List;

@Mapper
public interface AccountMapper {
    @SelectProvider(type = Provider.class)
    Long findOrgIdByUserName(String username);
    @SelectProvider(type = Provider.class)
    List<Long> findRoleIdByUserName(String username);
    @SelectProvider(type = Provider.class)
    List<Long> getPermitShowOrgids(List<Long> roleId);

    class Provider implements ProviderMethodResolver{
        public String findOrgIdByUserName(String username){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("SELECT sys_organization.id FROM sys_account ");
            stringBuffer.append("JOIN account_organization on account_organization.account_id=sys_account.id\n" +
                    " JOIN sys_organization on sys_organization.id=account_organization.organiztion_id ");
           stringBuffer.append("WHERE sys_organization.type=1 and sys_account.username= '"+username+"'");
           return stringBuffer.toString();
        }
        public String findRoleIdByUserName(String username){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("SELECT account_organization.organiztion_id FROM sys_account ");
            stringBuffer.append("JOIN account_organization on account_organization.account_id=sys_account.id\n" +
                    " INNER JOIN sys_organization ON sys_organization.id=account_organization.organiztion_id ");
            stringBuffer.append("WHERE sys_organization.type=3 and sys_account.username= '"+username+"'");
            return stringBuffer.toString();
        }
        public String getPermitShowOrgids(List<Long> roleId){


            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("SELECT  role_organization.orgnazition_id ");
            stringBuffer.append(" FROM `role_organization`");
            stringBuffer.append(" where role_organization.role_id IN ("+ ListUtils.list2In(roleId) +")");
            return stringBuffer.toString();
        }
    }
}
