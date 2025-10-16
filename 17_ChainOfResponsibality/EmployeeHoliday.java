public class EmployeeHoliday {
    static abstract class Designation{
        protected Designation sucessor ;
        public void setNextDesignationLevel(Designation sucessor){
            this.sucessor = sucessor ;
        }

        public abstract void handleHolidayRequest(int days) ;
    }

    static class teamLead extends Designation{
        private int numDaysCanApprove ;

        public teamLead(int numDaysCanApprove){
            this.numDaysCanApprove = numDaysCanApprove ;
        }

        @Override
        public void handleHolidayRequest(int days){
            if(days <= numDaysCanApprove){
                System.out.println("Team lead can approave " + days + " days holiday request");
            }
            else if(sucessor != null){
                sucessor.handleHolidayRequest(days);
            }
            else System.out.println("Request for " + days + " days cannot be approved");
        }
    }

    static class projectManager extends Designation{
        private int numDaysCanApprove ;

        public projectManager(int numDaysCanApprove){
            this.numDaysCanApprove = numDaysCanApprove ;
        }

        @Override
        public void handleHolidayRequest(int days){
            if(days <= numDaysCanApprove){
                System.out.println("Project Manager can approave " + days + " days holiday request");
            }
            else if(sucessor != null){
                sucessor.handleHolidayRequest(days);
            }
            else System.out.println("Request for " + days + " days cannot be approved");
        }

    }

    static class hr extends Designation{
        private int numDaysCanApprove ;

        public hr(int numDaysCanApprove){
            this.numDaysCanApprove = numDaysCanApprove ;
        }

        @Override
        public void handleHolidayRequest(int days){
            if(days <= numDaysCanApprove){
                System.out.println("HR can approave " + days + " days holiday request");
            }
            else if(sucessor != null){
                sucessor.handleHolidayRequest(days);
            }
            else System.out.println("Request for " + days + " days cannot be approved");
        }
      
}


    public static void main(String[] args) {
        Designation teamLead = new teamLead(2) ;
        Designation projecManager = new projectManager(5) ;
        Designation hr = new hr(15) ;

        teamLead.setNextDesignationLevel(projecManager);
        projecManager.setNextDesignationLevel(hr);

        teamLead.handleHolidayRequest(112);
    }

}