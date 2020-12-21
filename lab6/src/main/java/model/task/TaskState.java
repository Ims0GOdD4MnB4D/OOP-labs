package model.task;

public enum TaskState {
    OPEN {
        @Override
        public String getState() {
            return "OPENED";
        }
    }, ACTIVE {
        @Override
        public String getState() {
            return "ACTIVATED";
        }
    }, RESOLVED {
        @Override
        public String getState() {
            return "RESOLVED";
        }
    };

    public abstract String getState();
}
