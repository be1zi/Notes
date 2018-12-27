package notes.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Date;

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
    @Column(name = "Description")
    private String desc;

    @NotNull
    @Column(name = "Date")
    private Date insertDate;

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

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", insertDate=" + insertDate +
                '}';
    }
}