package ozguryazilim.movieCollection.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "movie_name")
	private String movieName;

	@Column(name = "publication_year")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate publicationYear;

	@Column(name = "type")
	private String type;

	@Column(name = "description")
	private String description;

	@Column(name = "media")
	private String media;



	@OneToMany(mappedBy = "movie")
	public List<Actor> actors;

	@OneToMany(mappedBy = "movie")
	public List<Language> languages;

}
