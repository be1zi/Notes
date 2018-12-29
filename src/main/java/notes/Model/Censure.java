package notes.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    @Override
    public String toString() {
        return "Censure{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", pattern='" + pattern + '\'' +
                '}';
    }
}
