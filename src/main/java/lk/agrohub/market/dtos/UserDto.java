package lk.agrohub.market.dtos;

import lk.agrohub.market.model.User;

public class UserDto {
	private User user;
	
	private String imagePath;

	public UserDto(User user, String imagePath) {
		super();
		this.user = user;
		this.imagePath = imagePath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
	
}
