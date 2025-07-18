// High-level modules should not depend on low-level modules. Both should depend on abstractions.
// Abstractions should not depend on details. Details should depend on abstractions.

public class DIP{

    public static void main(String[] args) {
        
        Database mySQLDatabase = new MySQLDatabase();
        Database mongoDB = new MongoDB();
        User_Service userService1 = new User_Service(mySQLDatabase);
        userService1.saveUser("Sample User Data for MySQL");

        User_Service userService2 = new User_Service(mongoDB);
        userService2.saveUser("Sample User Data for MongoDB");
    }
    public static class User_Service {
        private Database database;

        public User_Service(Database database) {
            this.database = database;
        }

        public void saveUser(String userData) {
            database.save(userData);
    }
    }

    // Define the Database interface
    public  interface Database {
        void save(String data);
    }

    public static class MySQLDatabase implements Database {
        @Override
        public void save(String data) {
            System.out.println("Saving data to MySQL database: " + data);
        }
    }

    public static class MongoDB implements Database {
        @Override
        public void save(String data) {
            System.out.println("Saving data to MongoDB database: " + data);
        }
    }

}