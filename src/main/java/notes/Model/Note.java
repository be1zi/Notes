package notes.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
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

    @Column(name = "DateString")
    private String insertDateString;

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
        setInsertDateString(stringFromDate(this.insertDate));
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

    public String getInsertDateString() {
        return insertDateString;
    }

    public void setInsertDateString(String insertDateString) {
        this.insertDateString = insertDateString;
    }

    private String stringFromDate(Date date) {

        String pattern = "yyyy-MM-dd";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(date);
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
