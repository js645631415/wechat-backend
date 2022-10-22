package com.dark.monitor.entity.system;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.EnumSet;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SYS_ORGANIZATION")
@DiscriminatorValue("10")
public  class OrganizationEntity extends AbstractAuditable<Long> {
    /** 名称 */
    @Column(name = "NAME")
    protected @Getter @Setter String name;
    /** 状态 */
    @Column(name = "STATUS")
    protected @Getter @Setter Integer status;
    /** 类型 1 机构 2 部门 3 角色*/
    @Column(name = "TYPE")
    protected @Getter @Setter Integer type;
    @JsonIgnore
    @ManyToOne @JoinColumn(name = "PARENT_ORGANIZATION")
    protected @Getter @Setter OrganizationEntity parentOrganization;


    public enum OrganizationEnum implements Enums<String> {
        未知("null"),机构("1"),部门("2"),角色("3");
        public  @Getter @JsonValue String code;
        OrganizationEnum(String code) {
            this.code = code;
        }
        public static OrganizationEnum creator(String code) {
            if (code != null) {
                EnumSet<OrganizationEnum> aenums = EnumSet.allOf(OrganizationEnum.class);
                for (OrganizationEnum aenum : aenums) {
                    if (aenum.code.equals(code)) {
                        return aenum;
                    }
                }
            }

            return OrganizationEnum.未知;
        }
    }
}
