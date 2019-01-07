package notes.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "Id", columnDefinition = "int default 0")
    private Long id;

    @NotNull
    @Column(name = "Login")
    private String login;

    @NotNull
    @Column(name = "Password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Note> notes;

    private boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Note> getNotes() {

        List<Note> list = new LinkedList<>();

        for (Note n : notes) {
            if (!n.isDeleted()) {
                list.add(n);
            }
        }

        return list;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void addNote(Note note) {
        if (this.notes == null) {
            this.notes = new LinkedList<>();
        }

        if (!this.notes.contains(note)) {
            this.notes.add(note);
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "UserController{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
