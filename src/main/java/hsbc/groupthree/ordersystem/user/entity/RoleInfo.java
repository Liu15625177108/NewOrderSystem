package hsbc.groupthree.ordersystem.user.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author : Jeff.Li
 * @Package: hsbc.groupthree.ordersystem.user.entity
 * @Program: NewOrderSystem
 * @Description: role info
 * @date : 2018年08月21日 16:09:12
 **/
@Entity
@Data
public class RoleInfo implements Serializable {

    @Id
    private String roleId;

    @NotNull
    private String roleName;

    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<UserInfo> userInfos;

    public RoleInfo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        RoleInfo roleInfo = (RoleInfo) o;
        return Objects.equals(roleId, roleInfo.roleId) &&
                Objects.equals(roleName, roleInfo.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
}
