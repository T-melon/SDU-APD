package com.wzt.tapm.entity;

import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 * Basic log info
 */
@AllArgsConstructor
@ToString
public class LogBean implements Serializable {

    @NonNull
    @Getter
    @Setter
    private int log_id;

    @NonNull
    @Getter
    @Setter
    private int demand_id;

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
    private String cer;

    @NonNull
    @Getter
    @Setter
    private String commit;

    @Tolerate
    public LogBean() {
    }

}

