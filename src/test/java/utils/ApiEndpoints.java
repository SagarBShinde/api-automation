package utils;

public enum ApiEndpoints {
	
	CreateMultipleUsers("/user/createWithArray"),
	GetUserByUserName("/user"),
	UpdateUser("/user"),
	FindPetByStatus("/pet/findByStatus"),
	UpdatePet("/pet"),
	AddPet("/pet"),
	GetPet("/pet");
	
	private String endPoint;
	
	ApiEndpoints(String e) {
		endPoint = e;
	}
	
	public String getEndPoint() {
		return this.endPoint;
	}
	
}
