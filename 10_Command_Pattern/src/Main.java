public class Main {
    public static void main(String[] args) {
        Light light = new Light() ;
        ICommand lightCommand = new LightCommand(light) ;
        Remote remoteControl = new Remote() ;
        remoteControl.setCommand(lightCommand) ;
        remoteControl.pressButton() ;
        remoteControl.pressButton() ;
        Fan fan = new Fan() ;
        FanCommand fanCommand = new FanCommand(fan) ;
        remoteControl.setCommand(fanCommand);
        remoteControl.pressButton() ;
        remoteControl.pressButton() ;
    }
}

interface ICommand{
    public void execute() ;
    public void undo() ;
}

class Light{
    public void on(){
        System.out.println("Light is on") ;
    }
    public void off(){
        System.out.println("Light is off") ;
    }
}

class Fan {
    public void on() {
        System.out.println("Fan is on");
    }

    public void off() {
        System.out.println("Fan is off");
    }
}

class LightCommand implements ICommand{
    Light light ;
    public LightCommand(Light light){
        this.light = light ;
    }
    public void execute(){
        light.on() ;
    }
    public void undo(){
        light.off() ;
    }
}

class FanCommand implements ICommand{
    Fan fan ;
    public FanCommand(Fan fan){
        this.fan = fan ;
    }
    public void execute(){
        fan.on() ;
    }
    public void undo(){
        fan.off() ;
    }
}

class Remote {
    Boolean isOn = false ;
    ICommand command ;
    public void setCommand(ICommand command){
        this.command = command ;
    }
    public void pressButton(){
        if (!isOn) {
            command.execute();
        } else {
            command.undo();
        }
        isOn = !isOn ;
    }
}
