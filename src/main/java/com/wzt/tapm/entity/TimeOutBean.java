package com.wzt.tapm.entity;

import lombok.*;
import lombok.experimental.Tolerate;

@AllArgsConstructor
@ToString
public class TimeOutBean {

    @NonNull
    @Getter
    @Setter
    int demand_id;

    @NonNull
    @Getter
    @Setter
    int status;

    @NonNull
    @Getter
    @Setter
    String ddl;

    @Tolerate
    public TimeOutBean() {
    }

}
