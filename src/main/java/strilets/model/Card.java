package strilets.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "CARD")
public class Card implements Serializable {

	public Card() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Column(name = "LOGIN", unique = true, nullable = false)
	private String login;

	@NotBlank
	@Size(max = 100)
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Size(max = 30)
	@Column(name = "NAME")
	private String name;

	@Size(max = 2147483647)
	@Column(name = "DESCRIPTION")
	private String description;

	@Size(max = 50)
	@Column(name = "PEOPLE")
	private String people;

	@Size(max = 50)
	@Column(name = "ADDRESS")
	private String address;

	@Size(max = 50)
	@Column(name = "EMAIL")
	private String email;

	@Size(max = 50)
	@Column(name = "PHONE")
	private String phone;

	@Size(max = 30)
	@Column(name = "FACEBOOK")
	private String facebook;

	@Size(max = 30)
	@Column(name = "TWITTER")
	private String twitter;

	@Size(max = 30)
	@Column(name = "INSTAGRAM")
	private String instagram;

	@NotNull
	@Size(max = 5)
	@Column(name = "ROLE", unique = true)
	private String role;

	@Column(name = "NAME_IMAGE")
	private String nameImage;

	@Column(name = "TYPE")
	private String type;

	@Lob
	@Column(name = "IMAGE")
	private byte[] image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNameImage() {
		return nameImage;
	}

	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}