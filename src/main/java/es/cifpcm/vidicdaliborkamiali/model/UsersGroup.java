package es.cifpcm.vidicdaliborkamiali.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users_groups")
public class UsersGroup {
    @EmbeddedId
    private UsersGroupId id;

    @MapsId("groupId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @MapsId("userName")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_name", nullable = false, referencedColumnName = "user_name")
    private User userName;

    public UsersGroupId getId() {
        return id;
    }

    public void setId(UsersGroupId id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getGroupUserName() {
        return userName;
    }

    public void setGroupUserName(User userName) {
        this.userName = userName;
    }


}