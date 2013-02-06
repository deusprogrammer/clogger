import com.jpcf.blog.*

class BootStrap {

    def init = { servletContext ->
		if (!User.findByUsername("root")) {
			def user = new User(username: "root", password: "password", firstName: "root", lastName: "root", userGroup: Group.SUPERUSER);
			user.save();
		}
    }
    def destroy = {
    }
}
