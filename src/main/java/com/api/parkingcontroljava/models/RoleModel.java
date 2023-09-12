package com.api.parkingcontroljava.models;

import com.api.parkingcontroljava.enums.RoleName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "TB_ROLE")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleModel implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    RoleName name;

    @Override
    public String getAuthority() {
        return this.name.toString();
    }
}