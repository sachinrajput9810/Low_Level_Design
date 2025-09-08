
// Strategy Design Pattern Example in Java
// It means always favour composition over inheritance
// In the family of alogithms put them in a class and use them as needed

    interface TalkableRobot{ 
        void talk();
    }

    class TalkableRobotImpl implements TalkableRobot {
        @Override
        public void talk() {
            System.out.println("I as a robot can talk");
        }
    }
    class NonTalkableRobotImpl implements TalkableRobot {
        @Override
        public void talk() {
            System.out.println("I as a robot cannot talk");
        }
    }

    interface WalkableRobot {
        void walk();
    }

    class WalkableRobotImpl implements WalkableRobot {
        @Override
        public void walk() {
            System.out.println("I as a robot can walk");
        }
    }

    class NonWalkableRobotImpl implements WalkableRobot {
        @Override
        public void walk() {
            System.out.println("I as a robot cannot walk");
        }
    }

    interface FlyableRobot {
        void fly();
    }

    class FlyableRobotImpl implements FlyableRobot {
        @Override
        public void fly() {
            System.out.println("I as a robot can fly");
        }
    }

    class NonFlyableRobotImpl implements FlyableRobot {
        @Override
        public void fly() {
            System.out.println("I as a robot cannot fly");
        }
    }

    interface Projectable{
        void projection();
    }
    class NormalProjectableImpl implements Projectable {
        @Override
        public void projection() {
            System.out.println("I as a robot can project normal images");
        }
    }
    class HDProjectableImpl implements Projectable {
        @Override
        public void projection() {
            System.out.println("I as a robot can project HD images");
        }    
    }

    class Robot{
        private TalkableRobot talkableRobot;
        private WalkableRobot walkableRobot;
        private FlyableRobot flyableRobot;
        private Projectable projectable;
        public Robot(TalkableRobot talkableRobot, WalkableRobot walkableRobot, FlyableRobot flyableRobot , Projectable projectable) {
            this.talkableRobot = talkableRobot;
            this.walkableRobot = walkableRobot;
            this.flyableRobot = flyableRobot;
            this.projectable = projectable;
        }

        public void talk() {
            talkableRobot.talk();
        }
        public void walk() {
            walkableRobot.walk();
        }
        public void fly() {
            flyableRobot.fly();
        }
        public void project() {
            projectable.projection();
        }
    }


public class StrategyDesignPattern {
   
   public static void main(String[] args) {
        Robot robotTyp1 = new Robot(
                new TalkableRobotImpl(),
                new WalkableRobotImpl(),
                new FlyableRobotImpl() ,
                new NormalProjectableImpl()
        );
        System.out.println("Robot Capabilities:");
        robotTyp1.talk();
        robotTyp1.walk();
        robotTyp1.fly();
        robotTyp1.project();


        System.out.println("-----------------------------");

        Robot workerRobot = new Robot(
                new NonTalkableRobotImpl(),
                new WalkableRobotImpl(),
                new FlyableRobotImpl() ,
                new HDProjectableImpl()
        );
        System.out.println("Worker Robot Capabilities:");
        workerRobot.talk();
        workerRobot.walk();
        workerRobot.fly();
        workerRobot.project();

    }


}