package com.wzt.tapm.entity;

import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.Date;

/**
 * Basic demand info(, , , )
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
    private int mode;

    public DemandBean(@NonNull String title, @NonNull String project, @NonNull String ctime, @NonNull String ddl, @NonNull String cer, @NonNull String doer, @NonNull int priority, @NonNull int mode) {
        this.title = title;
        this.project = project;
        this.ctime = ctime;
        this.ddl = ddl;
        this.cer = cer;
        this.doer = doer;
        this.priority = priority;
        this.mode = mode;
    }

    public DemandBean(@NonNull String title, @NonNull String project, @NonNull String ddl, @NonNull String doer, @NonNull int priority) {
        this.title = title;
        this.project = project;
        this.ddl = ddl;
        this.doer = doer;
        this.priority = priority;
    }

    @Tolerate
    public DemandBean() {
    }

}
