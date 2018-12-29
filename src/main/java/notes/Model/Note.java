package notes.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Note {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "Id")
    private long id;

    @NotNull
    @Column(name = "Title", length = 50)
    private String title;

    @NotNull
    @Column(name = "Description", columnDefinition = "TEXT")
    private String desc;

    @NotNull
    @Column(name = "Date")
    private Date insertDate;

    @OneToOne
    private User user;

    @NotNull
    @Column(name = "Deleted")
    private boolean deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", insertDate=" + insertDate +
                ", isDeleted=" + deleted +
                '}';
    }
}
