package notes.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Censure {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "Id")
    private long id;

    @NotNull
    @Column(name = "Text")
    private String text;

    @NotNull
    @Column(name = "Pattern")
    private String pattern;

    @NotNull
    @Column(name = "Deleted")
    private boolean deleted;

    @OneToOne
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Censure{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", pattern='" + pattern + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
