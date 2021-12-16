package com.wzt.tapm.entity;

import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 * Reset password
 */
@AllArgsConstructor
@ToString
public class ResetUserBean implements Serializable {

    @NonNull
    @Getter
    @Setter
    private String username;

    @NonNull
    @Getter
    @Setter
    private String old_password;

    @NonNull
    @Getter
    @Setter
    private String new_password;

    @Tolerate
    public ResetUserBean() {
    }

}
