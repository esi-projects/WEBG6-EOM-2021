package esi.webg6.g54895.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    @Id
    private String login;

    private String name;

    @OneToMany(mappedBy="author")
    @ToString.Exclude
    @JsonBackReference
    private Set<Track> tracks;

    @Transient
    private int nbOfTracks;

    public int getNbOfTracks() {
        return tracks.size();
    }

    public Artist(String login, String name) {
        this.login = login;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Artist artist = (Artist) o;
        return login != null && Objects.equals(login, artist.login);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
