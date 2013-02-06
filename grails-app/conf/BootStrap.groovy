import com.jpcf.blog.*

class BootStrap {

    def init = { servletContext ->
		if (!User.findByUsername("root")) {
			def user = new User(username: "root", password: "password", firstName: "", lastName: "", userGroup: Group.SUPERUSER);
			user.save();
		}
    }
    def destroy = {
    }
}
