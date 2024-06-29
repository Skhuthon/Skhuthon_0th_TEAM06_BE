package skhu.com.skhuthon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "loginId")
    private String loginId;

    @Column(name = "pw")
    private String pw;

    @Column(name = "name")
    private String name;

    @Column(name = "access")
    private boolean access;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    public Photo photo;

    public User(String loginId, String pw, String name) {
        this.loginId = loginId;
        this.pw = pw;
        this.name = name;
    }
}