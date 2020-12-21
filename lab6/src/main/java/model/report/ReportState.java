package model.report;

public enum ReportState {
    APPROVED {
        @Override
        public String getState() {
            return "Current state is APPROVED";
        }
    }, NOT_PROVEN {
        @Override
        public String getState() {
            return "Report state is NOT_PROVEN";
        }
    };

    public abstract String getState();
}
