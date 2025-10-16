public class COR {

    static abstract class MoneyHandler{
        protected MoneyHandler successor ;

        public void setNextMoneyHandler(MoneyHandler successor){
            this.successor = successor ;
        }

        public abstract void handleMoney(int amount) ;

    }

    static class ThousandHandler extends MoneyHandler{
        private int numNotes ;

        public ThousandHandler(int numNotes){
            this.numNotes = numNotes ;
        }

        @Override
        public void handleMoney(int amount){
            int notesRequired = amount/1000 ;
            if(notesRequired > numNotes){
                notesRequired = numNotes ;
                numNotes = 0 ;
            }
            else numNotes -= notesRequired ;

            if(notesRequired > 0){
                System.out.println("Dispensing " + notesRequired + " thousand notes")  ;
            }

            int remainingAmount = amount - notesRequired*1000 ;
            if(remainingAmount > 0){
                if(successor != null){
                    successor.handleMoney(remainingAmount);
                }
                else System.out.println("Reamining amount " + remainingAmount + " cannot be dispensed");
            }

        }



    }

    static class FiveHundredHandler extends MoneyHandler{
        private int numNotes ;

        public FiveHundredHandler(int numNotes){
            this.numNotes = numNotes ;
        }

        @Override
        public void handleMoney(int amount){
            int notesNeeded = amount/500 ;
            if(notesNeeded > numNotes){
                notesNeeded = numNotes ;
                numNotes = 0 ;
            }
            else numNotes -= notesNeeded ;

            if(notesNeeded > 0){
                System.out.println("Dispensing " + notesNeeded + " five hundred notes")  ;
            }

            int remainingAmount = amount - notesNeeded*500 ;
            if(remainingAmount > 0){
                if(successor != null){
                    successor.handleMoney(remainingAmount);
                }
                else System.out.println("Reamining amount " + remainingAmount + " cannot be dispensed");
            }
        }



    }
    static class HundredHandler extends MoneyHandler{
        private int numNotes ;

        public HundredHandler(int numNotes){
            this.numNotes = numNotes ;
        }

        @Override
        public void handleMoney(int amount){
            int notesNeeded = amount/100 ;
            if(notesNeeded > numNotes){
                notesNeeded = numNotes ;
                numNotes = 0 ;
            }
            else numNotes -= notesNeeded ;

            if(notesNeeded > 0){
                System.out.println("Dispensing " + notesNeeded + " hundred notes")  ;
            };

            int remainingAmount = amount - notesNeeded*100 ;
            if(remainingAmount > 0){
                if(successor != null){
                    successor.handleMoney(remainingAmount);
                }
                else System.out.println("Reamining amount " + remainingAmount + " cannot be dispensed");
            }
        }


    }



    public static void main(String[] args) {
        ThousandHandler thousandHandler = new ThousandHandler(5) ;
        HundredHandler hundredHandler = new HundredHandler(5) ;
        FiveHundredHandler fiveHundredHandler = new FiveHundredHandler(5) ;

        thousandHandler.setNextMoneyHandler(fiveHundredHandler);
        fiveHundredHandler.setNextMoneyHandler(hundredHandler);

        thousandHandler.handleMoney(6000);
    }

}