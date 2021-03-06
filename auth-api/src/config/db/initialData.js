import bcrypt from "bcrypt";
import User from "../../modules/user/model/User";

export async function createInitialData() {
    try {
        await User.sync({ force:true });

        let password = await bcrypt.hash("123456", 10);

        await User.create({
            name: "User Test",
            email: "teste@email.com",
            password: password,
        });

        await User.create({
            name: "User Test1",
            email: "teste1@email.com",
            password: password,
        });
    } catch (err) {
        console.log(err);
    }
}