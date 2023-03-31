import ra.bussiness.entity.User;
import ra.bussiness.impl.UserImp;

public class Main {
    public static void main(String[] args) {

        UserImp userImpl = new UserImp();
        User admin = new User(1,"admin123","123456","",true,true);
        userImpl.create(admin);
    }
}