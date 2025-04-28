package com.Upload.Phu.RequestDTO;

public class ChangePasswordRequestDTO {

        private String username;
        private String oldPassword;
        private String newPassword;

        // getters và setters
        public String getUsername() { return username; }
        public String getOldPassword() { return oldPassword; }
        public String getNewPassword() { return newPassword; }

        public void setUsername(String username) { this.username = username; }
        public void setOldPassword(String oldPassword) { this.oldPassword = oldPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

}
