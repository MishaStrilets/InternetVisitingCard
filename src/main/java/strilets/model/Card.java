/**
 * Class POJO which implements methods for get and set card.
 * 
 * @author Misha Strilets
 * @version 1.0
 */
package strilets.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class Card implements Serializable {

	public Card() {
	}

	public Card(User user) {
		this.setLogin(user.getLogin());
		this.setName(user.getName());
		this.setDescription(user.getDescription());
		this.setPeople(user.getPeople());
		this.setAddress(user.getAddress());
		this.setEmail(user.getEmail());
		this.setPhone(user.getPhone());
		this.setLinkedin(user.getLinkedin());
		this.setFacebook(user.getFacebook());
		this.setTwitter(user.getTwitter());
		this.setInstagram(user.getInstagram());
		this.setNameImage(user.getNameImage());
		this.setFontColor(user.getFontColor());
		this.setBackgroundColor(user.getBackgroundColor());
		this.setVisible(user.getVisible());
	}

	private String login;

	@Size(max = 50)
	private String name;

	@Size(max = 2147483647)
	private String description;

	@Size(max = 50)
	private String people;

	@Size(max = 50)
	private String address;

	@Size(max = 50)
	private String email;

	@Size(max = 50)
	private String phone;

	@Size(max = 100)
	private String linkedin;

	@Size(max = 100)
	private String facebook;

	@Size(max = 100)
	private String twitter;

	@Size(max = 100)
	private String instagram;

	MultipartFile file;

	private String nameImage;

	private String fontColor;

	private String backgroundColor;

	private Boolean visible;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getNameImage() {
		return nameImage;
	}

	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}