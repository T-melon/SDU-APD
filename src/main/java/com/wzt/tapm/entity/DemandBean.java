package com.wzt.tapm.entity;

import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 * Basic demand info
 */

@AllArgsConstructor
@ToString
public class DemandBean implements Serializable {

    @NonNull
    @Getter
    @Setter
    private int demand_id;

    @NonNull
    @Getter
    @Setter
    private String title;

    @NonNull
    @Getter
    @Setter
    private String project;

    @NonNull
    @Getter
    @Setter
    private String ctime;

    @NonNull
    @Getter
    @Setter
    private String ddl;

    @NonNull
    @Getter
    @Setter
    private String cer;

    @NonNull
    @Getter
    @Setter
    private String doer;

    @NonNull
    @Getter
    @Setter
    private int priority;

    @NonNull
    @Getter
    @Setter
    private int status;

    @Getter
    @Setter
    private String address;


    @Getter
    @Setter
    private String docu;

    public DemandBean(@NonNull String title, @NonNull String project, @NonNull String ctime, @NonNull String ddl, @NonNull String cer, @NonNull String doer, @NonNull int priority, @NonNull int status) {
        this.title = title;
        this.project = project;
        this.ctime = ctime;
        this.ddl = ddl;
        this.cer = cer;
        this.doer = doer;
        this.priority = priority;
        this.status = status;
    }

    public DemandBean(@NonNull String title, @NonNull String project, @NonNull String ddl, @NonNull String doer, @NonNull int priority, @NonNull int status) {
        this.title = title;
        this.project = project;
        this.ddl = ddl;
        this.doer = doer;
        this.priority = priority;
        this.status = status;
    }

    @Tolerate
    public DemandBean() {
    }

}
