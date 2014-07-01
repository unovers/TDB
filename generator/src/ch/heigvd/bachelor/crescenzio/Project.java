package ch.heigvd.bachelor.crescenzio;

public class Project {
	private String name;
	private String packageName;
	private String author;
	private String organisation;

	public Project(String name, String packageName, String author,
			String organisation) {
		this.name = name;
		this.packageName = packageName;
		this.author = author;
		this.organisation = organisation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
}
