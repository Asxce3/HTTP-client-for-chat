package Command;

public class CommandGetRooms implements Command {

    public Receiver receiver;

    public CommandGetRooms(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.getRooms();
    }
}
