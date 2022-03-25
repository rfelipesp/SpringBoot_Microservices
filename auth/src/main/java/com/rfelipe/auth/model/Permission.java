package com.rfelipe.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 3529022090509398661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Override
    public String getAuthority() {
        return this.description;
    }

}
