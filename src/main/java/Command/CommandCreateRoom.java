package Command;

public class CommandCreateRoom implements Command {

    public Receiver receiver;

    public CommandCreateRoom(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.createRoom();
    }
}
