package Command;

public class CommandShowMessage implements Command {
    private Receiver receiver;

    public CommandShowMessage(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(){
        receiver.showMessage();
    }
}
