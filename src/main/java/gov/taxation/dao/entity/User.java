package gov.taxation.dao.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder(alphabetic = true)
public class User {
    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private RoleType role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                userName.equals(user.userName) &&
                password.equals(user.password) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                email.equals(user.email) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, firstName, lastName, email, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    public static Builder builder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long userId) {
            User.this.id = userId;
            return this;
        }

        public Builder userName(String userName) {
            User.this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            User.this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            User.this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            User.this.email = email;
            return this;
        }

        public Builder role(RoleType role) {
            User.this.role = role;
            return this;
        }

        public User build() {
            return User.this;
        }

    }
}

