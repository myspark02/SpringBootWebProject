package yju.wdb.codingwithscpark.entity;

import javax.persistence.*;

@Entity
public class MovieImage extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inum;
	
	private String uuid;
	private String imageName;
	private String path;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;
	
	

	public MovieImage() {
		super();
	}
	public MovieImage(String uuid, String imageName, Movie movie) {
		this.uuid = uuid;
		this.imageName = imageName;
		this.movie = movie;
	}
	public MovieImage(Long inum, String uuid, String imagName, String path, Movie movie) {
		super();
		this.inum = inum;
		this.uuid = uuid;
		this.imageName = imagName;
		this.path = path;
		this.movie = movie;
	}
	
	public MovieImage(String path, String imageName, String uuid, Movie movie) {
		this.path = path;
		this.imageName = imageName;
		this.uuid = uuid;
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "MovieImage [inum=" + inum + ", uuid=" + uuid + ", imagName=" + imageName + ", path=" + path +  "]";
	}

	public Long getInum() {
		return inum;
	}


	public String getUuid() {
		return uuid;
	}


	public String getImageName() {
		return imageName;
	}

	public String getPath() {
		return path;
	}


	public Movie getMovie() {
		return movie;
	}

	
}
