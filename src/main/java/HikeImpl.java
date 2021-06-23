import java.util.*;

public class HikeImpl implements Hike {
    private String hikeName;
    private int duration;
    private int numberOfParticipants;
    private List<HikeDay> hikeDays = new LinkedList<>();

    public HikeImpl(String hikeName, int duration, int numberOfParticipants) {
        this.hikeName = hikeName;
        this.duration = duration;
        this.numberOfParticipants = numberOfParticipants;
        for (int i = 0; i < duration; i++) {
            hikeDays.add(new HikeDayImpl(this));
        }
    }

    @Override
    public String getHikeName() {
        return hikeName;
    }

    @Override
    public void setHikeName(String hikeName) {
        this.hikeName = hikeName;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
        if (duration > 0){
            if (this.duration > duration){
                hikeDays = hikeDays.subList(0, duration);
            } else {
                for (int i = this.duration - 1; i < duration; i++ ) {
                    hikeDays.add(new HikeDayImpl(this));
                }
            }
            this.duration = duration;
        } else {
            throw new IllegalArgumentException("Duration must be greater than zero");
        }
    }

    @Override
    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    @Override
    public void setNumberOfParticipants(int numberOfParticipants) {
        if (numberOfParticipants > 0) {
            this.numberOfParticipants = numberOfParticipants;
        } else {
            throw new IllegalArgumentException("Number of participants must be greater than zero");
        }
    }

    @Override
    public List<HikeDay> getHikeDays() {
        return hikeDays;
    }

    @Override
    public HikeDay getHikeDay(int n) {
        return hikeDays.get(n);
    }

    @Override
    public void addHikeDay() {
        hikeDays.add(new HikeDayImpl(this));
        duration++;
    }

    @Override
    public void moveHikeDay(int hikeDayNumber, int position) {
        hikeDays.set(position, hikeDays.get(hikeDayNumber));
    }

    @Override
    public void removeHikeDay(int numberOfDay) {
        hikeDays.remove(numberOfDay);
        duration--;
    }

    @Override
    public String toString() {
        return "Hike: " + hikeName;
    }

}
