package dto;

public class Top10Join {
	private Integer userId;
	private Integer dramaId;
	private String birthYear;
	private String name;
	private String VOD;
	private Integer numberOfEpisode;
	private String contentRating;
	private float rating;
	private String genre;
	private String cast;
	private String ranking;
	
	public Top10Join(String name, String VOD,
			Integer numberOfEpisode, String contentRating, float rating, String genre, String cast, String ranking) {
		super();
		this.name = name;
		this.VOD = VOD;
		this.numberOfEpisode = numberOfEpisode;
		this.contentRating = contentRating;
		this.rating = rating;
		this.genre = genre;
		this.cast = cast;
		this.ranking = ranking;
	}
	public String getContentRating() {
		return contentRating;
	}
	public void setContentRating(String contentRating) {
		this.contentRating = contentRating;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDramaId() {
		return dramaId;
	}
	public void setDramaId(Integer dramaId) {
		this.dramaId = dramaId;
	}
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVOD() {
		return VOD;
	}
	public void setVOD(Integer vOD) {
		this.VOD = VOD;
	}
	public Integer getNumberOfEpisode() {
		return numberOfEpisode;
	}
	public void setNumberOfEpisode(Integer numberOfEpisode) {
		this.numberOfEpisode = numberOfEpisode;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return ranking.substring(1) + "." + "[제목:" + name + " 플랫폼:" + VOD + " 회차수:" + numberOfEpisode + " 장르:" + genre + " 평점:" + rating + 
				" 관람등급:" + contentRating.substring(0, 3) + " 출연:" + cast +"]\n";
	}
	
}
