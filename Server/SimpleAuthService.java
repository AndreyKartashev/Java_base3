package server;

import java.sql.SQLException;
import java.util.ArrayList;

public class SimpleAuthService implements AuthService {

    public ServiceAuthDB database;

    public static ArrayList<UserData> userlist;

    public SimpleAuthService() {
        userlist = new ArrayList<UserData>();
        database = new ServiceAuthDB();
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        for (UserData user : userlist) {
            if(user.login.equals(login) && user.password.equals(password)){
                return user.nickname;
            }
        }
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nickname) throws SQLException {
        int i = 0;
        for (UserData user : userlist) {
            if (user.login.equals(login)) {
                if (user.nickname.equals(nickname)) {
                    return false;
                } else {
                    userlist.remove(i);
                    userlist.add(new UserData(login, password, nickname));
                    database.delete(login);
                    database.insert(login, password, nickname);
                    return true;
                }
            }
            i += 1;
        }
        userlist.add(new UserData(login, password, nickname));
        database.insert(login, password, nickname);
        return true;
    }
}