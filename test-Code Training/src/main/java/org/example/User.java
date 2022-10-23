package org.example;

public class User {
    private String password;

    public void initPassword(PasswordGenerator passwordGenerator){
        // as-is
        // RandomPasswordGenerator randomPasswordGenerator = randomPasswordGenerator;

        // to be
        String randomPassword = passwordGenerator.generatePassword();
        /**
         * 비밀번호는 최소 8자 이상 12자 이하여야 한다.
         * */
        int length = randomPassword.length();
        if(length >= 8 && length <= 12){
            this.password = randomPassword;
        }
    }

    public String getPassword() {
        return password;
    }
}
