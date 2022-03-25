package com.rfelipe.auth.data.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO implements Serializable {

    private static final long serialVersionUID = -800369327360380513L;

    private String userName;
    private String password;

}
