import spark.Spark;
import handlers.*;

public class Server {
    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            Spark.port(port);

            Spark.externalStaticFileLocation("web");

            createRoutes();

            Spark.init();
            System.out.println("Listening on port" + port);
        } catch(ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            System.err.println("Specify port number as a command line parameter");
        }
    }

    private static void createRoutes() {

        Spark.post("/user", (req, res) -> new RegisterHandler().handle(req, res));
        Spark.post("/session", (req, res) -> new LoginHandler().handle(req, res));
        Spark.delete("/session", (req, res) -> new LogoutHandler().handle(req, res));
        Spark.get("/game", (req, res) -> new ListGamesHandler().handle(req,res));
        Spark.post("/game", (req, res) ->  new CreateGameHandler().handle(req, res));
        Spark.put("/game", (req, res) -> new JoinGameHandler().handle(req, res));
        Spark.delete("/db", (req, res) -> new ClearAllHandler().handle(res));

    }
}
