package domain;

public class review {
    String description;

    Integer userId;

    public review(Integer userId, String description) {
        this.description = description;
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User with id " + userId.toString() + "said" + description;
    }
}
