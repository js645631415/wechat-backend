package com.dark.monitor.entity.system;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;



@Entity
@Table(name = "SYS_RESOURCE")
public class ResourceEntity extends AbstractAuditable<Long> {
    /** 父级资源 */
    @ManyToOne @JoinColumn(name = "PID")
    private @Getter @Setter ResourceEntity parent;
    /** 资源名称 */
    @Column(name = "NAME")
    private @Getter @Setter String name;
    /** 资源类型 */
    @Column(name = "TYPE")
    private @Getter @Setter Integer type;
    /** 资源路径 */
    @Column(name = "PATH")
    private @Getter @Setter String path;
    /** 访问路由 */
    @Column(name = "ROUTE")
    private @Getter @Setter String route;
    /** 资源权限 */
    @Column(name = "PERMISSION")
    private @Getter @Setter String permission;
    /** 活动状态 */
    @Column(name = "ACTIVE")
    private @Getter @Setter boolean active;
    /** 通用状态 */
    @Column(name = "STATUS")

    private @Getter @Setter Integer status;
    /** 排序 */
    @Column(name = "SORT")
    private @Getter @Setter int sort;
    /** 排序串 */
    @Column(name = "SORT_PATH")
    private @Getter @Setter String sortPath;







}
